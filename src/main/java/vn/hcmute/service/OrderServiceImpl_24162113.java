package vn.hcmute.service;

import java.util.List;
import java.util.Map;
import vn.hcmute.dao.IOrderDAO_24162113;
import vn.hcmute.dao.OrderDAOImpl_24162113;
import vn.hcmute.dto.Cart_24162113;
import vn.hcmute.entity.Order_24162113;
import vn.hcmute.entity.User_24162113;

public class OrderServiceImpl_24162113 implements IOrderService_24162113 {
    private final IOrderDAO_24162113 orderDAO = new OrderDAOImpl_24162113();

    @Override
    public Order_24162113 placeCodOrder(User_24162113 user, Cart_24162113 cart, String receiver, String phone, String address, String note) {
        if (user == null) {
            throw new OrderException_24162113("Vui lòng đăng nhập để đặt hàng");
        }
        if (cart == null || cart.isEmpty()) {
            throw new OrderException_24162113("Giỏ hàng đang trống");
        }
        if (receiver.isEmpty() || receiver.length() > 100) {
            throw new OrderException_24162113("Vui lòng nhập họ tên người nhận (tối đa 100 ký tự)");
        }
        if (!phone.matches("^(0|\\+84)\\d{9}$")) {
            throw new OrderException_24162113("Số điện thoại không hợp lệ (ví dụ: 0912345678)");
        }
        if (address.isEmpty() || address.length() > 255) {
            throw new OrderException_24162113("Vui lòng nhập địa chỉ giao hàng (tối đa 255 ký tự)");
        }
        if (note.length() > 255) {
            throw new OrderException_24162113("Ghi chú tối đa 255 ký tự");
        }
        Order_24162113 order = new Order_24162113();
        order.setReceiver(receiver);
        order.setPhone(phone);
        order.setAddress(address);
        order.setNote(note.isEmpty() ? null : note);
        order.setPaymentMethod(Order_24162113.COD);
        order.setStatus(Order_24162113.PENDING);
        try {
            return orderDAO.create(user.getUsername(), order, new java.util.ArrayList<>(cart.getItems()));
        } catch (IllegalStateException e) {
            throw new OrderException_24162113(e.getMessage());
        }
    }

    @Override
    public List<Order_24162113> findByUser(String username) {
        return orderDAO.findByUser(username);
    }

    @Override
    public List<Order_24162113> findByUserAndStatus(String username, String status) {
        return orderDAO.findByUserAndStatus(username, status);
    }

    @Override
    public Map<String, Long> countByStatus(String username) {
        return orderDAO.countByStatus(username);
    }

    @Override
    public Order_24162113 findByIdAndUser(long orderId, String username) {
        return orderDAO.findByIdAndUser(orderId, username);
    }
}
