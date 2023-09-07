package com.hoangtan.moneycards.service;

import com.hoangtan.moneycards.dao.IncomeSourceDAO;
import com.hoangtan.moneycards.dao.UserDAO;
import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
//import com.hoangtan.moneycards.service.mapper.IncomeSourceMapper;
import com.hoangtan.moneycards.service.mapper.IncomeSourceMapper;
import com.hoangtan.moneycards.service.model.IncomeSourceDTO;
import com.hoangtan.moneycards.service.model.MoneyCardDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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
                .user(userDAO.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_UNAUTHORIZED_ACCESS,
                        ErrorMessage.UNAUTHORIZED_ACCESS)))
                .build();
        return incomeSourceMapper.toDTO(incomeSourceDAO.create(incomeSource));
    }

    public IncomeSourceDTO findById(Long id) throws ResourceNotFoundException {
        IncomeSource incomeSource = incomeSourceDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.KEY_INCOME_SOURCE_NOT_FOUND, ErrorMessage.INCOME_SOURCE_NOT_FOUND));
        return incomeSourceMapper.toDTO(incomeSource);
    }

    public List<IncomeSourceDTO> findByUser(String email) throws ResourceNotFoundException {
        User user = userDAO.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS));
        List<IncomeSource> incomeSourceList = incomeSourceDAO.findByUser(user.getId());
        return incomeSourceMapper.toDTOList(incomeSourceList);
    }
}
