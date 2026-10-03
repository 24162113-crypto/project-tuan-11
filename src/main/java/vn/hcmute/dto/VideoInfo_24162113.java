package vn.hcmute.dto;

import vn.hcmute.entity.Video_24162113;

public class VideoInfo_24162113 {
    private final String videoId;
    private final String title;
    private final String poster;
    private final int views;
    private final String description;
    private final boolean active;
    private final Integer categoryId;
    private final String categoryName;
    private final long shares;
    private final long likes;
    private final long price;
    private final int stock;

    public VideoInfo_24162113(Video_24162113 video, long shares, long likes) {
        this.videoId = video.getVideoId();
        this.title = video.getTitle();
        this.poster = video.getPoster();
        this.views = video.getViews() == null ? 0 : video.getViews();
        this.description = video.getDescription();
        this.active = Boolean.TRUE.equals(video.getActive());
        this.categoryId = video.getCategory() == null ? null : video.getCategory().getCategoryId();
        this.categoryName = video.getCategory() == null ? "" : video.getCategory().getCategoryname();
        this.shares = shares;
        this.likes = likes;
        this.price = video.getPrice() == null ? 0 : Math.max(0, video.getPrice());
        this.stock = video.getStock() == null ? 0 : Math.max(0, video.getStock());
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public int getViews() {
        return views;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public long getShares() {
        return shares;
    }

    public long getLikes() {
        return likes;
    }

    public long getPrice() {
        return price;
    }

    public String getPriceText() {
        return vn.hcmute.util.MoneyUtil_24162113.format(price);
    }

    public int getStock() {
        return stock;
    }

    public boolean isInStock() {
        return stock > 0;
    }

    public int getMaxQuantity() {
        return Math.min(stock, CartItem_24162113.MAX_PER_ITEM);
    }
}
