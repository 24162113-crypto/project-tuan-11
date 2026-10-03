package vn.hcmute.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import vn.hcmute.util.MoneyUtil_24162113;

@Entity(name = "OrderItem")
@Table(name = "OrderItems")
public class OrderItem_24162113 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemId")
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId")
    private Order_24162113 order;

    @Column(name = "VideoId", length = 50)
    private String videoId;

    @Column(name = "Title", length = 200)
    private String title;

    @Column(name = "Price")
    private Long price;

    @Column(name = "Quantity")
    private Integer quantity;

    public OrderItem_24162113() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Order_24162113 getOrder() {
        return order;
    }

    public void setOrder(Order_24162113 order) {
        this.order = order;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPriceText() {
        return MoneyUtil_24162113.format(price == null ? 0 : price);
    }

    public String getSubtotalText() {
        long p = price == null ? 0 : price;
        int q = quantity == null ? 0 : quantity;
        return MoneyUtil_24162113.format(p * q);
    }
}
