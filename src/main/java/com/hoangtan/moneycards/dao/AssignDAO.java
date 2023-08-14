package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.Assign;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AssignDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public Assign create(Assign assign) {
        em.persist(assign);
        return assign;
    }
}
