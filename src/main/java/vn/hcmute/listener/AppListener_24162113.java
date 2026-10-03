package vn.hcmute.listener;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import vn.hcmute.entity.Category_24162113;
import vn.hcmute.entity.Favorite_24162113;
import vn.hcmute.entity.Share_24162113;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.entity.Video_24162113;
import vn.hcmute.util.JPAUtil_24162113;

@WebListener
public class AppListener_24162113 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        JPAUtil_24162113.getFactory();
        seed();
        backfillCommerce();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        JPAUtil_24162113.close();
    }

    private User_24162113 newUser(String username, String fullname, String email, String phone, boolean admin) {
        User_24162113 user = new User_24162113();
        user.setUsername(username);
        user.setPassword("123456");
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAdmin(admin);
        user.setActive(true);
        return user;
    }

    private void backfillCommerce() {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<Video_24162113> videos = em.createQuery("select v from Video v where v.price is null or v.stock is null order by v.videoId", Video_24162113.class).getResultList();
            if (videos.isEmpty()) {
                return;
            }
            em.getTransaction().begin();
            int i = 0;
            for (Video_24162113 video : videos) {
                i++;
                if (video.getPrice() == null) {
                    video.setPrice(30000L + (i % 10) * 5000L);
                }
                if (video.getStock() == null) {
                    video.setStock(i % 5 == 0 ? 3 : 10 + (i % 4) * 5);
                }
            }
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

    private void seed() {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            long users = em.createQuery("select count(u) from Users u", Long.class).getSingleResult();
            if (users > 0) {
                return;
            }
            em.getTransaction().begin();
            User_24162113 admin = newUser("admin", "Quản trị viên", "admin@gmail.com", "0900000001", true);
            User_24162113 tan = newUser("tan", "Nguyễn Hữu Tân", "tan@gmail.com", "0900000002", false);
            User_24162113 user01 = newUser("user01", "Người dùng 01", "user01@gmail.com", "0900000003", false);
            em.persist(admin);
            em.persist(tan);
            em.persist(user01);
            User_24162113[] fans = {tan, user01, admin};

            String[][] names = {{"Âm nhạc", "AM_NHAC"}, {"Phim ảnh", "PHIM_ANH"}, {"Thể thao", "THE_THAO"}, {"Giáo dục", "GIAO_DUC"}};
            List<Category_24162113> categories = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                Category_24162113 category = new Category_24162113();
                category.setCategoryname(names[i][0]);
                category.setCategorycode(names[i][1]);
                category.setImages("p" + (i + 1) + ".svg");
                category.setStatus(true);
                em.persist(category);
                categories.add(category);
            }

            int index = 0;
            for (int c = 0; c < categories.size(); c++) {
                for (int k = 1; k <= 8; k++) {
                    index++;
                    Video_24162113 video = new Video_24162113();
                    video.setVideoId(String.format("V%03d", index));
                    video.setTitle(names[c][0] + " - Video " + k);
                    video.setPoster("p" + (index % 6 + 1) + ".svg");
                    video.setViews(100 + index * 37);
                    video.setDescription("Mô tả nội dung của video " + names[c][0] + " số " + k);
                    video.setActive(true);
                    video.setCategory(categories.get(c));
                    em.persist(video);
                    for (int s = 0; s <= index % 5; s++) {
                        Share_24162113 share = new Share_24162113();
                        share.setEmails("ban" + s + "@gmail.com");
                        share.setSharedDate(LocalDate.now().minusDays(s));
                        share.setUser(fans[s % fans.length]);
                        share.setVideo(video);
                        em.persist(share);
                    }
                    for (int f = 0; f <= index % 4; f++) {
                        Favorite_24162113 favorite = new Favorite_24162113();
                        favorite.setLikedDate(LocalDate.now().minusDays(f));
                        favorite.setUser(fans[f % fans.length]);
                        favorite.setVideo(video);
                        em.persist(favorite);
                    }
                }
            }
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
