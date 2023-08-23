package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.AssignDAO;
import com.hoangtan.moneycards.dao.IncomeSourceDAO;
import com.hoangtan.moneycards.dao.MoneyCardDAO;
import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.Assign;
import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.MinusException;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.service.mapper.AssignMapper;
import com.hoangtan.moneycards.service.mapper.JarTypeAttributeConverter;
import com.hoangtan.moneycards.service.mapper.MoneyCardMapper;
import com.hoangtan.moneycards.service.model.AssignDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AssignService {

    @Inject
    private AssignMapper assignMapper;

    @Inject
    private AssignDAO assignDAO;

    @Inject
    private MoneyCardService moneyCardService;

    @Inject
    private UserDAO userDAO;

    @Inject
    private MoneyCardDAO moneyCardDAO;

    @Inject
    private IncomeSourceDAO incomeSourceDAO;

    @Inject
    private MoneyCardMapper moneyCardMapper;


    private JarTypeAttributeConverter jarTypeAttributeConverter = new JarTypeAttributeConverter();

    @Transactional
    public List<AssignDTO> create(AssignDTO assignDTO, String email) throws ResourceNotFoundException, MinusException {
        List<MoneyCardDTO> moneyCardDTOList = moneyCardService.findByUser(email);

        IncomeSource incomeSource = incomeSourceDAO.findById(assignDTO.getIncomeSourceId()).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_INCOME_SOURCE_NOT_FOUND, ErrorMessage.INCOME_SOURCE_NOT_FOUND));
        List<Assign> assignList = new ArrayList<>();

        for (MoneyCardDTO moneyCardDTO : moneyCardDTOList
        ) {
            Assign assign = Assign.builder()
                    .assignedTime(LocalDateTime.now())
                    .incomeSource(incomeSource)
                    .build();

            if (jarTypeAttributeConverter.convertToDatabaseColumn(moneyCardDTO.getJarType()) != 7) {

                MoneyCard moneyCard = moneyCardMapper.toEntity(moneyCardDTO);
                moneyCard.setBalance(moneyCard.getBalance() + assignDTO.getAmount() * moneyCardDTO.getPercentage());
                moneyCard.setUser(userDAO.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS)));
                moneyCardDAO.update(moneyCard);

                assign.setAmount(assignDTO.getAmount() * moneyCard.getPercentage());
                assign.setMoneyCard(moneyCard);
                assignList.add(assignDAO.create(assign));
                incomeSourceDAO.update(incomeSource);

                if (incomeSource.getBalance() - assign.getAmount() < 0) {
                    throw new MinusException(ErrorMessage.KEY_INCOME_SOURCE_NOT_NEGATIVE, ErrorMessage.INCOME_SOURCE_NOT_NEGATIVE);
                } else incomeSource.setBalance(incomeSource.getBalance() - assign.getAmount());

            }

        }


        return assignMapper.toDTOList(assignList);
    }
}
