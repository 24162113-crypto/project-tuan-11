package vn.hcmute.dto;

import java.util.List;
import vn.hcmute.entity.Category_24162113;

public class CategoryBlock_24162113 {
    private final Category_24162113 category;
    private final List<VideoInfo_24162113> videos;
    private final long total;
    private final int page;
    private final int totalPages;
    private final String otherParams;

    public CategoryBlock_24162113(Category_24162113 category, List<VideoInfo_24162113> videos, long total, int page, int totalPages, String otherParams) {
        this.category = category;
        this.videos = videos;
        this.total = total;
        this.page = page;
        this.totalPages = totalPages;
        this.otherParams = otherParams;
    }

    public Category_24162113 getCategory() {
        return category;
    }

    public List<VideoInfo_24162113> getVideos() {
        return videos;
    }

    public long getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getOtherParams() {
        return otherParams;
    }
}
