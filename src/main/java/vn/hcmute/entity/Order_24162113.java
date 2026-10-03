package vn.hcmute.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import vn.hcmute.util.MoneyUtil_24162113;

@Entity(name = "PurchaseOrder")
@Table(name = "Orders")
public class Order_24162113 implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String COD = "COD";
    public static final String PENDING = "PENDING";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username")
    private User_24162113 user;

    @Column(name = "Receiver", length = 100)
    private String receiver;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "Note", length = 255)
    private String note;

    @Column(name = "Total")
    private Long total;

    @Column(name = "PaymentMethod", length = 20)
    private String paymentMethod;

    @Column(name = "Status", length = 20)
    private String status;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem_24162113> items = new ArrayList<>();

    public Order_24162113() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User_24162113 getUser() {
        return user;
    }

    public void setUser(User_24162113 user) {
        this.user = user;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem_24162113> getItems() {
        return items;
    }

    public void setItems(List<OrderItem_24162113> items) {
        this.items = items;
    }

    public String getTotalText() {
        return MoneyUtil_24162113.format(total == null ? 0 : total);
    }

    public String getCreatedAtText() {
        return createdAt == null ? "" : createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getPaymentText() {
        return COD.equals(paymentMethod) ? "Thanh toán khi nhận hàng (COD)" : String.valueOf(paymentMethod);
    }

    public OrderStatus_24162113 getStatusEnum() {
        return OrderStatus_24162113.fromCode(status);
    }

    public String getStatusText() {
        OrderStatus_24162113 s = getStatusEnum();
        return s == null ? String.valueOf(status) : s.getText();
    }

    public String getStatusClass() {
        OrderStatus_24162113 s = getStatusEnum();
        return s == null ? "label-default" : s.getLabelClass();
    }

    /** Bước hiện tại trong luồng giao hàng (1..6), 0 nếu đơn bị hủy/hoàn. */
    public int getStatusStep() {
        OrderStatus_24162113 s = getStatusEnum();
        return s == null ? 0 : s.getStep();
    }

    public int getItemCount() {
        int count = 0;
        for (OrderItem_24162113 item : items) {
            count += item.getQuantity() == null ? 0 : item.getQuantity();
        }
        return count;
    }
}
