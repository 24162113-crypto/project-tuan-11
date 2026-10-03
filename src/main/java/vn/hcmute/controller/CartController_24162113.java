package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import vn.hcmute.dto.Cart_24162113;
import vn.hcmute.service.CartServiceImpl_24162113;
import vn.hcmute.service.ICartService_24162113;
import vn.hcmute.util.FlashUtil_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet("/cart")
public class CartController_24162113 extends HttpServlet {
    private final ICartService_24162113 cartService = new CartServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart_24162113 cart = cartService.getCart(req.getSession());
        List<String> notes = cartService.refresh(cart);
        FlashUtil_24162113.consume(req);
        if (!notes.isEmpty()) {
            req.setAttribute("notes", notes);
        }
        req.getRequestDispatcher("/WEB-INF/views/user/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart_24162113 cart = cartService.getCart(session);
        String action = StringUtil_24162113.clean(req.getParameter("action"));
        String id = StringUtil_24162113.clean(req.getParameter("id"));
        int quantity = StringUtil_24162113.toInt(req.getParameter("quantity"), 1);
        String error = null;
        String success = null;
        switch (action) {
            case "add":
                error = cartService.add(cart, id, quantity);
                success = "Đã thêm sản phẩm vào giỏ hàng";
                break;
            case "update":
                error = cartService.update(cart, id, quantity);
                success = "Đã cập nhật giỏ hàng";
                break;
            case "remove":
                cartService.remove(cart, id);
                success = "Đã xóa sản phẩm khỏi giỏ hàng";
                break;
            case "clear":
                cartService.clear(cart);
                success = "Đã xóa toàn bộ giỏ hàng";
                break;
            default:
                break;
        }
        if (error != null) {
            FlashUtil_24162113.error(session, error);
        } else if (success != null) {
            FlashUtil_24162113.ok(session, success);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
