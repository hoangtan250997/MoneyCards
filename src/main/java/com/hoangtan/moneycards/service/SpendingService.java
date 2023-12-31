package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.*;
import com.hoangtan.moneycards.entity.Assign;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.entity.Spending;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.service.mapper.SpendingMapper;
import com.hoangtan.moneycards.service.model.AssignDTO;
import com.hoangtan.moneycards.service.model.SpendingDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class SpendingService {

    @Inject
    private SpendingMapper spendingMapper;

    @Inject
    private SpendingDAO spendingDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private MoneyCardDAO moneyCardDAO;

    public SpendingDTO create(SpendingDTO spendingDTO, String email) throws ResourceNotFoundException {
        Spending spending = Spending.builder()
                .amount(spendingDTO.getAmount())
                .spendingTime(spendingDTO.getSpendingTime()==null? LocalDate.now():spendingDTO.getSpendingTime())
                .moneyCard(moneyCardDAO.findById(spendingDTO.getMoneyCardId()).orElseThrow(()->new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND,ErrorMessage.CARD_NOT_FOUND)))
                .purpose(spendingDTO.getPurpose())
                .user(userDAO.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS)))
                .build();
        MoneyCard moneyCard = moneyCardDAO.findById(spendingDTO.getMoneyCardId()).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND, ErrorMessage.CARD_NOT_FOUND));
        moneyCard.setBalance(moneyCard.getBalance()-spendingDTO.getAmount());
        moneyCardDAO.update(moneyCard);

        return spendingMapper.toDTO(spendingDAO.create(spending));
    }

    public List<SpendingDTO> findByJarTypeAndUser(int jarType,String email) throws ResourceNotFoundException {
        User user = userDAO.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS));
        List<Spending> spendingList = spendingDAO.findByJarTypeAndUser(jarType, user.getId());
        return spendingMapper.toDTOList(spendingList);
    }
}
