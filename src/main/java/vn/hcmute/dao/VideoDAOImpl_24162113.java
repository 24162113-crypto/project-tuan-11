package vn.hcmute.dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vn.hcmute.dto.VideoInfo_24162113;
import vn.hcmute.entity.Video_24162113;
import vn.hcmute.util.JPAUtil_24162113;

public class VideoDAOImpl_24162113 implements IVideoDAO_24162113 {
    private static final String SELECT = "select v from Video v left join fetch v.category ";

    @Override
    public List<VideoInfo_24162113> findPage(int page, int size) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<Video_24162113> videos = em.createQuery(SELECT + "order by v.videoId", Video_24162113.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
            return toInfos(em, videos);
        } finally {
            em.close();
        }
    }

    @Override
    public long count() {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.createQuery("select count(v) from Video v", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<VideoInfo_24162113> findByCategory(int categoryId, int page, int size) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<Video_24162113> videos = em.createQuery(SELECT + "where v.category.categoryId = :cid and v.active = true order by v.videoId", Video_24162113.class)
                    .setParameter("cid", categoryId)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
            return toInfos(em, videos);
        } finally {
            em.close();
        }
    }

    @Override
    public long countByCategory(int categoryId) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.createQuery("select count(v) from Video v where v.category.categoryId = :cid and v.active = true", Long.class)
                    .setParameter("cid", categoryId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public VideoInfo_24162113 findInfoById(String videoId) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            List<Video_24162113> videos = em.createQuery(SELECT + "where v.videoId = :id", Video_24162113.class)
                    .setParameter("id", videoId)
                    .getResultList();
            List<VideoInfo_24162113> infos = toInfos(em, videos);
            return infos.isEmpty() ? null : infos.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public Video_24162113 findById(String videoId) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            return em.find(Video_24162113.class, videoId);
        } finally {
            em.close();
        }
    }

    @Override
    public void save(Video_24162113 video) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(video);
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

    @Override
    public void delete(String videoId) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from Share s where s.video.videoId = :id").setParameter("id", videoId).executeUpdate();
            em.createQuery("delete from Favorite f where f.video.videoId = :id").setParameter("id", videoId).executeUpdate();
            Video_24162113 video = em.find(Video_24162113.class, videoId);
            if (video != null) {
                em.remove(video);
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

    @Override
    public void increaseViews(String videoId) {
        EntityManager em = JPAUtil_24162113.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("update Video v set v.views = coalesce(v.views, 0) + 1 where v.videoId = :id")
                    .setParameter("id", videoId)
                    .executeUpdate();
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

    private List<VideoInfo_24162113> toInfos(EntityManager em, List<Video_24162113> videos) {
        List<VideoInfo_24162113> result = new ArrayList<>();
        if (videos.isEmpty()) {
            return result;
        }
        List<String> ids = new ArrayList<>();
        for (Video_24162113 video : videos) {
            ids.add(video.getVideoId());
        }
        Map<String, Long> shares = counts(em, "select s.video.videoId, count(s) from Share s where s.video.videoId in :ids group by s.video.videoId", ids);
        Map<String, Long> likes = counts(em, "select f.video.videoId, count(f) from Favorite f where f.video.videoId in :ids group by f.video.videoId", ids);
        for (Video_24162113 video : videos) {
            result.add(new VideoInfo_24162113(video, shares.getOrDefault(video.getVideoId(), 0L), likes.getOrDefault(video.getVideoId(), 0L)));
        }
        return result;
    }

    private Map<String, Long> counts(EntityManager em, String jpql, List<String> ids) {
        Map<String, Long> map = new HashMap<>();
        List<Object[]> rows = em.createQuery(jpql, Object[].class).setParameter("ids", ids).getResultList();
        for (Object[] row : rows) {
            map.put((String) row[0], (Long) row[1]);
        }
        return map;
    }
}
