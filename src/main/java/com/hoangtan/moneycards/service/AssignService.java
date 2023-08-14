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

@Stateless
public class AssignService {

    @Inject
    private AssignMapper assignMapper;

    @Inject
    private AssignDAO assignDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private MoneyCardDAO moneyCardDAO;

    @Inject
    private IncomeSourceDAO incomeSourceDAO;

    public AssignDTO create(AssignDTO assignDTO) {
        Assign assign = Assign.builder()
                .amount(assignDTO.getAmount())
                .assignedTime(LocalDate.now())
//                .moneyCard(moneyCardDAO.findById(assignDTO.getMoneyCardId()))
                .moneyCard(moneyCardDAO.findById(1))
//                .incomeSource(incomeSourceDAO.findById(assignDTO.getIncomeSourceId()))
                .incomeSource(incomeSourceDAO.findById(1))

                .build();
        return assignMapper.toDTO(assignDAO.create(assign));
    }
}
