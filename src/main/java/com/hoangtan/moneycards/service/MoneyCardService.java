package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.MoneyCardDAO;
import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.service.mapper.MoneyCardMapper;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class MoneyCardService {

    @Inject
    private MoneyCardMapper moneyCardMapper;

    @Inject
    private MoneyCardDAO moneyCardDAO;

    @Inject
    private UserDAO userDAO;

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
}
