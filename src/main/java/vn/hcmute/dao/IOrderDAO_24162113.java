package vn.hcmute.dao;

import java.util.List;
import java.util.Map;
import vn.hcmute.dto.CartItem_24162113;
import vn.hcmute.entity.Order_24162113;

public interface IOrderDAO_24162113 {
    Order_24162113 create(String username, Order_24162113 order, List<CartItem_24162113> items);

    List<Order_24162113> findByUser(String username);

    /** Lọc theo trạng thái; status null/rỗng = tất cả. */
    List<Order_24162113> findByUserAndStatus(String username, String status);

    /** Số đơn theo từng trạng thái (key = mã trạng thái viết hoa). */
    Map<String, Long> countByStatus(String username);

    Order_24162113 findByIdAndUser(long orderId, String username);
}
