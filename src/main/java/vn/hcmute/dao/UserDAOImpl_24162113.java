package vn.hcmute.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.util.JPAUtil_24162113;

public class UserDAOImpl_24162113 implements IUserDAO_24162113 {
    @Override
    public User_24162113 findById(String username) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.find(User_24162113.class, username);
        } finally {
            em.close();
        }
    }

    @Override
    public User_24162113 findActiveByEmail(String email) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<User_24162113> list = em.createQuery("select u from Users u where lower(u.email) = :email and u.active = true", User_24162113.class)
                    .setParameter("email", email.toLowerCase())
                    .setMaxResults(1)
                    .getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public void save(User_24162113 user) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
