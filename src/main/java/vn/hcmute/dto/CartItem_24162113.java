package vn.hcmute.dto;

import java.io.Serializable;
import vn.hcmute.util.MoneyUtil_24162113;

public class CartItem_24162113 implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MAX_PER_ITEM = 10;

    private final String videoId;
    private String title;
    private String poster;
    private long price;
    private int stock;
    private int quantity;

    public CartItem_24162113(String videoId, String title, String poster, long price, int stock, int quantity) {
        this.videoId = videoId;
        this.title = title;
        this.poster = poster;
        this.price = price;
        this.stock = stock;
        this.quantity = quantity;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLimit() {
        return Math.min(stock, MAX_PER_ITEM);
    }

    public long getSubtotal() {
        return price * quantity;
    }

    public String getPriceText() {
        return MoneyUtil_24162113.format(price);
    }

    public String getSubtotalText() {
        return MoneyUtil_24162113.format(getSubtotal());
    }
}
