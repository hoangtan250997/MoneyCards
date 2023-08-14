package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class MoneyCardDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<MoneyCard> criteriaQuery = criteriaBuilder.createQuery(MoneyCard.class);

    Root<MoneyCard> element = criteriaQuery.from(MoneyCard.class);

    public MoneyCard create(MoneyCard moneyCard) {
        em.persist(moneyCard);
        return moneyCard;
    }

    public MoneyCard findById(long id) {
        criteriaQuery.where(criteriaBuilder.equal(element.get("id"), id));
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}