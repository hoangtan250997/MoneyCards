package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.AssignDAO;
import com.hoangtan.moneycards.dao.IncomeSourceDAO;
import com.hoangtan.moneycards.dao.MoneyCardDAO;
import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.Assign;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.service.mapper.AssignMapper;
import com.hoangtan.moneycards.service.model.AssignDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public AssignDTO create(AssignDTO assignDTO) throws ResourceNotFoundException {
        Assign assign = Assign.builder()
                .amount(assignDTO.getAmount())
                .assignedTime(LocalDateTime.now())
                .moneyCard(moneyCardDAO.findById(assignDTO.getMoneyCardId()).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND, ErrorMessage.CARD_NOT_FOUND)))
                .incomeSource(incomeSourceDAO.findById(assignDTO.getIncomeSourceId()).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_INCOME_SOURCE_NOT_FOUND, ErrorMessage.INCOME_SOURCE_NOT_FOUND)))
                .build();
        MoneyCard moneyCard = moneyCardDAO.findById(assignDTO.getMoneyCardId()).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND, ErrorMessage.CARD_NOT_FOUND));
        moneyCard.setBalance(moneyCard.getBalance()+assignDTO.getAmount());
        moneyCardDAO.deposit(moneyCard);
        return assignMapper.toDTO(assignDAO.create(assign));
    }
}
