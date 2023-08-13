package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.IncomeSourceDAO;
import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
//import com.hoangtan.moneycards.service.mapper.IncomeSourceMapper;
import com.hoangtan.moneycards.service.mapper.IncomeSourceMapper;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class IncomeSourceService {

    @Inject
    private IncomeSourceMapper incomeSourceMapper;

    @Inject
    private IncomeSourceDAO incomeSourceDAO;

    @Inject
    private UserDAO userDAO;

    public IncomeSourceDTO create(IncomeSourceDTO incomeSourceDTO, String email) throws ResourceNotFoundException {
        IncomeSource incomeSource = IncomeSource.builder()
                .name(incomeSourceDTO.getName())
                .balance(incomeSourceDTO.getBalance())
                .user(userDAO.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_SKILL_NOT_FOUND,
                        ErrorMessage.SKILL_NOT_FOUND)))
                .build();
        return incomeSourceMapper.toDTO(incomeSourceDAO.create(incomeSource));
    }
}
