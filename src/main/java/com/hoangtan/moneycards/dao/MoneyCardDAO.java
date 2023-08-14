package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.IncomeSource;
import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

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

//    public MoneyCard findById(long id) throws ResourceNotFoundException {
//        try {
//            criteriaQuery.where(criteriaBuilder.equal(element.get("id"), id));
//            return em.createQuery(criteriaQuery).getSingleResult();
//        } catch (Exception e){
//            throw new ResourceNotFoundException(ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.DUPLICATED_TOPIC_NAME);
//        }
//    }

    public Optional<MoneyCard> findById(Long id) {
        List<MoneyCard> moneyCardList = em.createQuery("SELECT t FROM MoneyCard t " +
                        "WHERE t.id = :id ", MoneyCard.class)
                .setParameter("id", id)
                .getResultList();

        return moneyCardList.isEmpty() ? Optional.empty() : Optional.of(moneyCardList.get(0));
    }
}
