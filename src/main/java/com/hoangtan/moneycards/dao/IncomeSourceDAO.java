package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.entity.User;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class IncomeSourceDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<IncomeSource> criteriaQuery = criteriaBuilder.createQuery(IncomeSource.class);

    Root<IncomeSource> element = criteriaQuery.from(IncomeSource.class);

    public IncomeSource create(IncomeSource incomeSource) {
        em.persist(incomeSource);
        return incomeSource;
    }

    public IncomeSource findById(long id) throws ResourceNotFoundException {
        try {
            criteriaQuery.where(criteriaBuilder.equal(element.get("id"), id));
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.DUPLICATED_TOPIC_NAME);
        }
    }
}
