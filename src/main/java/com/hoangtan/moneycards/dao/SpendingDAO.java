package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.Spending;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SpendingDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public Spending create(Spending spending) {
        em.persist(spending);
        return spending;
    }
}
