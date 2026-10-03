package vn.hcmute.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vn.hcmute.dto.CartItem_24162113;
import vn.hcmute.entity.Order_24162113;
import vn.hcmute.entity.OrderItem_24162113;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.entity.Video_24162113;
import vn.hcmute.util.JPAUtil_24162113;

public class OrderDAOImpl_24162113 implements IOrderDAO_24162113 {
    @Override
    public Order_24162113 create(String username, Order_24162113 order, List<CartItem_24162113> items) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            em.getTransaction().begin();
            List<CartItem_24162113> sorted = new ArrayList<>(items);
            sorted.sort(Comparator.comparing(CartItem_24162113::getVideoId));
            long total = 0;
            for (CartItem_24162113 item : sorted) {
                Video_24162113 video = em.find(Video_24162113.class, item.getVideoId(), LockModeType.PESSIMISTIC_WRITE);
                if (video == null || !Boolean.TRUE.equals(video.getActive())) {
                    throw new IllegalStateException("Sản phẩm \"" + item.getTitle() + "\" không còn được bán");
                }
                int stock = video.getStock() == null ? 0 : video.getStock();
                if (item.getQuantity() < 1 || stock < item.getQuantity()) {
                    throw new IllegalStateException("Sản phẩm \"" + video.getTitle() + "\" chỉ còn " + stock + " trong kho");
                }
                long price = video.getPrice() == null ? 0 : video.getPrice();
                video.setStock(stock - item.getQuantity());
                OrderItem_24162113 line = new OrderItem_24162113();
                line.setOrder(order);
                line.setVideoId(video.getVideoId());
                line.setTitle(video.getTitle());
                line.setPrice(price);
                line.setQuantity(item.getQuantity());
                order.getItems().add(line);
                total += price * item.getQuantity();
            }
            order.setTotal(total);
            order.setCreatedAt(LocalDateTime.now());
            order.setUser(em.getReference(User_24162113.class, username));
            em.persist(order);
            em.getTransaction().commit();
            return order;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Order_24162113> findByUser(String username) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.createQuery("select distinct o from PurchaseOrder o left join fetch o.items where o.user.username = :u order by o.orderId desc", Order_24162113.class)
                    .setParameter("u", username)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Order_24162113> findByUserAndStatus(String username, String status) {
        if (status == null || status.trim().isEmpty()) {
            return findByUser(username);
        }
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.createQuery("select distinct o from PurchaseOrder o left join fetch o.items where o.user.username = :u and upper(trim(o.status)) = :s order by o.orderId desc", Order_24162113.class)
                    .setParameter("u", username)
                    .setParameter("s", status.trim().toUpperCase())
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Map<String, Long> countByStatus(String username) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<Object[]> rows = em.createQuery("select upper(trim(o.status)), count(o) from PurchaseOrder o where o.user.username = :u group by upper(trim(o.status))", Object[].class)
                    .setParameter("u", username)
                    .getResultList();
            Map<String, Long> result = new HashMap<>();
            for (Object[] row : rows) {
                if (row[0] != null) {
                    result.put((String) row[0], (Long) row[1]);
                }
            }
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public Order_24162113 findByIdAndUser(long orderId, String username) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<Order_24162113> list = em.createQuery("select distinct o from PurchaseOrder o left join fetch o.items where o.orderId = :id and o.user.username = :u", Order_24162113.class)
                    .setParameter("id", orderId)
                    .setParameter("u", username)
                    .getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }
}
