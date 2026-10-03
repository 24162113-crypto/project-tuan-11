package vn.hcmute.service;

import java.util.List;
import java.util.Map;
import vn.hcmute.dto.Cart_24162113;
import vn.hcmute.entity.Order_24162113;
import vn.hcmute.entity.User_24162113;

public interface IOrderService_24162113 {
    Order_24162113 placeCodOrder(User_24162113 user, Cart_24162113 cart, String receiver, String phone, String address, String note);

    List<Order_24162113> findByUser(String username);

    List<Order_24162113> findByUserAndStatus(String username, String status);

    Map<String, Long> countByStatus(String username);

    Order_24162113 findByIdAndUser(long orderId, String username);
}
