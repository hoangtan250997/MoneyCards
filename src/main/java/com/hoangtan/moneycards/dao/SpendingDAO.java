package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.JarType;
import com.hoangtan.moneycards.entity.Spending;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class SpendingDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public Spending create(Spending spending) {
        em.persist(spending);
        return spending;
    }

    public List<Spending> findByJarTypeAndUser(JarType jarType, Long userId) {
        return em.createQuery("SELECT s FROM Spending s " +
                        "WHERE s.user.id = :userId AND s.moneyCard.jarType = :jarType", Spending.class)
                .setParameter("userId", userId)
                .setParameter("jarType", jarType)
                .getResultList();
    }

    public List<Spending> findByUser(Long userId) {
        return em.createQuery("SELECT s FROM Spending s WHERE s.user.id = :userId", Spending.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Spending> findBetweenTwoDays(long userId, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Spending> criteriaQuery = criteriaBuilder.createQuery(Spending.class);
        Root<Spending> element = criteriaQuery.from(Spending.class);

        Predicate userIdCondition = criteriaBuilder.equal(element.get("user"), userId);
        Predicate timeCondition = criteriaBuilder.between(element.get("spendingTime"), startDate, endDate);

        Predicate andCondition = criteriaBuilder.and(userIdCondition, timeCondition);
        criteriaQuery.where(andCondition);

        return em.createQuery(criteriaQuery).getResultList();

    }
}
