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
import vn.hcmute.entity.Order_24162113;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.service.CartServiceImpl_24162113;
import vn.hcmute.service.ICartService_24162113;
import vn.hcmute.service.IOrderService_24162113;
import vn.hcmute.service.OrderException_24162113;
import vn.hcmute.service.OrderServiceImpl_24162113;
import vn.hcmute.util.FlashUtil_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet("/checkout")
public class CheckoutController_24162113 extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/user/checkout.jsp";
    private final ICartService_24162113 cartService = new CartServiceImpl_24162113();
    private final IOrderService_24162113 orderService = new OrderServiceImpl_24162113();

    private User_24162113 currentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session == null ? null : (User_24162113) session.getAttribute("currentUser");
    }

    private boolean requireLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (currentUser(req) != null) {
            return true;
        }
        req.getSession().setAttribute("afterLogin", "/checkout");
        resp.sendRedirect(req.getContextPath() + "/login?need=1");
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!requireLogin(req, resp)) {
            return;
        }
        HttpSession session = req.getSession();
        Cart_24162113 cart = cartService.getCart(session);
        List<String> notes = cartService.refresh(cart);
        if (!notes.isEmpty() || cart.isEmpty()) {
            if (!notes.isEmpty()) {
                FlashUtil_24162113.error(session, String.join("; ", notes));
            }
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        User_24162113 user = currentUser(req);
        req.setAttribute("receiver", user.getFullname() == null ? "" : user.getFullname());
        req.setAttribute("phone", user.getPhone() == null ? "" : user.getPhone());
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!requireLogin(req, resp)) {
            return;
        }
        HttpSession session = req.getSession();
        Cart_24162113 cart = cartService.getCart(session);
        if (cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        String receiver = StringUtil_24162113.clean(req.getParameter("receiver"));
        String phone = StringUtil_24162113.clean(req.getParameter("phone"));
        String address = StringUtil_24162113.clean(req.getParameter("address"));
        String note = StringUtil_24162113.clean(req.getParameter("note"));
        try {
            if (!"COD".equals(req.getParameter("paymentMethod"))) {
                throw new OrderException_24162113("Phương thức thanh toán không hợp lệ");
            }
            Order_24162113 order = orderService.placeCodOrder(currentUser(req), cart, receiver, phone, address, note);
            cartService.clear(cart);
            resp.sendRedirect(req.getContextPath() + "/order?id=" + order.getOrderId() + "&placed=1");
        } catch (OrderException_24162113 e) {
            cartService.refresh(cart);
            if (cart.isEmpty()) {
                FlashUtil_24162113.error(session, e.getMessage());
                resp.sendRedirect(req.getContextPath() + "/cart");
                return;
            }
            req.setAttribute("error", e.getMessage());
            req.setAttribute("receiver", receiver);
            req.setAttribute("phone", phone);
            req.setAttribute("address", address);
            req.setAttribute("note", note);
            req.getRequestDispatcher(VIEW).forward(req, resp);
        }
    }
}
