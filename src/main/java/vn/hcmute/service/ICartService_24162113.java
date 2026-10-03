package vn.hcmute.service;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import vn.hcmute.dto.Cart_24162113;

public interface ICartService_24162113 {
    Cart_24162113 getCart(HttpSession session);

    String add(Cart_24162113 cart, String videoId, int quantity);

    String update(Cart_24162113 cart, String videoId, int quantity);

    void remove(Cart_24162113 cart, String videoId);

    void clear(Cart_24162113 cart);

    List<String> refresh(Cart_24162113 cart);
}
