package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MoneyCardDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public MoneyCard create(MoneyCard moneyCard) {
        em.persist(moneyCard);
        return moneyCard;
    }
}
