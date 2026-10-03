package vn.hcmute.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vn.hcmute.dao.CategoryDAOImpl_24162113;
import vn.hcmute.dao.ICategoryDAO_24162113;
import vn.hcmute.dao.IVideoDAO_24162113;
import vn.hcmute.dao.VideoDAOImpl_24162113;
import vn.hcmute.dto.CategoryBlock_24162113;
import vn.hcmute.dto.PageResult_24162113;
import vn.hcmute.dto.VideoInfo_24162113;
import vn.hcmute.entity.Category_24162113;
import vn.hcmute.entity.Video_24162113;

public class VideoServiceImpl_24162113 implements IVideoService_24162113 {
    private final IVideoDAO_24162113 videoDAO = new VideoDAOImpl_24162113();
    private final ICategoryDAO_24162113 categoryDAO = new CategoryDAOImpl_24162113();

    private static int totalPagesOf(long total, int size) {
        return (int) Math.max(1, (total + size - 1) / size);
    }

    private static int clamp(int page, int totalPages) {
        return Math.min(Math.max(page, 1), totalPages);
    }

    @Override
    public PageResult_24162113<VideoInfo_24162113> findPage(int page, int size) {
        long total = videoDAO.count();
        int totalPages = totalPagesOf(total, size);
        int current = clamp(page, totalPages);
        return new PageResult_24162113<>(videoDAO.findPage(current, size), current, totalPages, total);
    }

    @Override
    public long count() {
        return videoDAO.count();
    }

    @Override
    public List<CategoryBlock_24162113> buildBlocks(Map<Integer, Integer> requestedPages, int size) {
        List<Category_24162113> categories = categoryDAO.findAllActive();
        Map<Integer, Long> totals = new HashMap<>();
        Map<Integer, Integer> pages = new HashMap<>();
        Map<Integer, Integer> pageCounts = new HashMap<>();
        for (Category_24162113 category : categories) {
            int id = category.getCategoryId();
            long total = videoDAO.countByCategory(id);
            int totalPages = totalPagesOf(total, size);
            totals.put(id, total);
            pageCounts.put(id, totalPages);
            pages.put(id, clamp(requestedPages.getOrDefault(id, 1), totalPages));
        }
        List<CategoryBlock_24162113> blocks = new ArrayList<>();
        for (Category_24162113 category : categories) {
            int id = category.getCategoryId();
            StringBuilder others = new StringBuilder();
            for (Category_24162113 other : categories) {
                if (other.getCategoryId() != id) {
                    others.append('p').append(other.getCategoryId()).append('=').append(pages.get(other.getCategoryId())).append('&');
                }
            }
            List<VideoInfo_24162113> videos = videoDAO.findByCategory(id, pages.get(id), size);
            blocks.add(new CategoryBlock_24162113(category, videos, totals.get(id), pages.get(id), pageCounts.get(id), others.toString()));
        }
        return blocks;
    }

    @Override
    public VideoInfo_24162113 viewDetail(String videoId) {
        if (videoId == null || videoDAO.findById(videoId) == null) {
            return null;
        }
        videoDAO.increaseViews(videoId);
        return videoDAO.findInfoById(videoId);
    }

    @Override
    public Video_24162113 findById(String videoId) {
        return videoId == null || videoId.isEmpty() ? null : videoDAO.findById(videoId);
    }

    @Override
    public void save(Video_24162113 video) {
        videoDAO.save(video);
    }

    @Override
    public void delete(String videoId) {
        videoDAO.delete(videoId);
    }
}
