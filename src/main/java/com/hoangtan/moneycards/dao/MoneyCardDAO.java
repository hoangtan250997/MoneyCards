package com.hoangtan.moneycards.dao;

import com.hoangtan.moneycards.entity.MoneyCard;
import com.hoangtan.moneycards.exception.ErrorMessage;
import com.hoangtan.moneycards.exception.ResourceNotFoundException;
import com.hoangtan.moneycards.service.mapper.JarTypeAttributeConverter;
import com.hoangtan.moneycards.service.mapper.MoneyCardMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class MoneyCardDAO {
    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    @Inject
    private UserDAO userDAO;

    public MoneyCard create(MoneyCard moneyCard) {
        this.em.persist(moneyCard);
        return moneyCard;
    }

    public Optional<MoneyCard> findById(long id) throws ResourceNotFoundException {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<MoneyCard> criteriaQuery = criteriaBuilder.createQuery(MoneyCard.class);
        Root<MoneyCard> element = criteriaQuery.from(MoneyCard.class);
        try {
            criteriaQuery.where(criteriaBuilder.equal(element.get("id"), id));
            return Optional.ofNullable(em.createQuery(criteriaQuery).getSingleResult());
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorMessage.KEY_CARD_NOT_FOUND, ErrorMessage.CARD_NOT_FOUND);
        }
    }

    public List<MoneyCard> findByUser(Long id) {
        List<MoneyCard> moneyCardList = em.createQuery("SELECT t FROM MoneyCard t " +
                        "WHERE t.user.id = :id ", MoneyCard.class)
                .setParameter("id", id)
                .getResultList();

        return moneyCardList;
    }

    public MoneyCard deposit(MoneyCard moneyCard) {
        em.merge(moneyCard);
        return moneyCard;
    }

}
