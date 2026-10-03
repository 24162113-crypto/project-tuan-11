package vn.hcmute.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import vn.hcmute.entity.Category_24162113;
import vn.hcmute.util.JPAUtil_24162113;

public class CategoryDAOImpl_24162113 implements ICategoryDAO_24162113 {
    @Override
    public List<Category_24162113> findAll() {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.createQuery("select c from Category c order by c.categoryId", Category_24162113.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category_24162113> findAllActive() {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.createQuery("select c from Category c where c.status = true order by c.categoryId", Category_24162113.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Category_24162113 findById(int categoryId) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.find(Category_24162113.class, categoryId);
        } finally {
            em.close();
        }
    }
}
