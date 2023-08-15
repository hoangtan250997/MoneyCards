package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.MoneyCardDAO;
import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.service.mapper.JarTypeAttributeConverter;
import com.hoangtan.moneycards.service.mapper.MoneyCardMapper;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class MoneyCardService {

    @Inject
    private MoneyCardMapper moneyCardMapper;

    @Inject
    private MoneyCardDAO moneyCardDAO;

    @Inject
    private UserDAO userDAO;
    private JarTypeAttributeConverter jarTypeAttributeConverter = new JarTypeAttributeConverter();


    public MoneyCardDTO create(MoneyCardDTO moneyCardDTO, String email) throws ResourceNotFoundException {
        MoneyCard moneyCard = MoneyCard.builder()
                .jarType(moneyCardDTO.getJarType())
                .balance((double) 0)
                .percentage(moneyCardDTO.getPercentage())
                .user(userDAO.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_SKILL_NOT_FOUND,
                        ErrorMessage.SKILL_NOT_FOUND)))
                .build();
        return moneyCardMapper.toDTO(moneyCardDAO.create(moneyCard));
    }

    public MoneyCardDTO findById(Long id) throws ResourceNotFoundException {
        MoneyCard moneyCard = moneyCardDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND, ErrorMessage.CARD_NOT_FOUND));
        return moneyCardMapper.toDTO(moneyCard);
    }

    public List<MoneyCardDTO> findByUser(String email) throws ResourceNotFoundException {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS));
        List<MoneyCard> moneyCard = moneyCardDAO.findByUser(user.getId());
        return moneyCardMapper.toDTOList(moneyCard);
    }

    public MoneyCardDTO findByJarTypeAndUserId(int jarType, String email) throws ResourceNotFoundException {
        List<MoneyCardDTO> moneyCardDTOList = findByUser(email);
        MoneyCardDTO returnMoneyCardDTO = new MoneyCardDTO();
        try {
             returnMoneyCardDTO = moneyCardDTOList.stream()
                    .filter(moneyCardDTO -> moneyCardDTO.getJarType() == jarTypeAttributeConverter.convertToEntityAttribute(jarType))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception e){
            throw new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND,ErrorMessage.CARD_NOT_FOUND);
        }

        return returnMoneyCardDTO;
    }


}
