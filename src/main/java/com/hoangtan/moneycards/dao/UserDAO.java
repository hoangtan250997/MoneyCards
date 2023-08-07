package com.hoangtan.moneycards.dao;


import com.hoangtan.moneycards.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDAO {

    @PersistenceContext(name = "moneycards")
    private EntityManager em;

    public User create(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        List<User> userEntityList = em.createQuery("SELECT u FROM UserDTO u " +
                        "WHERE LOWER(trim(both from u.email)) LIKE LOWER(trim(both from :email))", User.class)
                .setParameter("email", email)
                .getResultList();

        return userEntityList.isEmpty() ? Optional.empty() : Optional.of(userEntityList.get(0));
    }
}
