package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.Spending;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SpendingDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public Spending create(Spending spending) {
        em.persist(spending);
        return spending;
    }

    public List<Spending> findByJarTypeAndUser(int jarType, Long userId) {
        return em.createQuery("SELECT s FROM Spending s " +
                        "WHERE s.user.id = :userId AND s.jarType = :jarType", Spending.class)
                .setParameter("userId", userId)
                .setParameter("jarType", jarType)
                .getResultList();
    }

    public List<Spending> findByUser(Long userId) {
        return em.createQuery("SELECT s FROM Spending s WHERE s.user.id = :userId", Spending.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
