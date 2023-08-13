package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class IncomeSourceDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public IncomeSource create(IncomeSource incomeSource) {
        em.persist(incomeSource);
        return incomeSource;
    }
}
