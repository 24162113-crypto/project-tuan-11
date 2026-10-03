package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import vn.hcmute.entity.Order_24162113;
import vn.hcmute.entity.OrderStatus_24162113;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.service.IOrderService_24162113;
import vn.hcmute.service.OrderServiceImpl_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet(urlPatterns = {"/orders", "/order"})
public class OrderController_24162113 extends HttpServlet {
    private final IOrderService_24162113 orderService = new OrderServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User_24162113 user = session == null ? null : (User_24162113) session.getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        if ("/order".equals(req.getServletPath())) {
            Order_24162113 order = orderService.findByIdAndUser(StringUtil_24162113.toLong(req.getParameter("id"), -1), user.getUsername());
            if (order == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            req.setAttribute("order", order);
            req.setAttribute("placed", "1".equals(req.getParameter("placed")));
            req.getRequestDispatcher("/WEB-INF/views/user/order-detail.jsp").forward(req, resp);
            return;
        }
        OrderStatus_24162113 filter = OrderStatus_24162113.fromCode(req.getParameter("status"));
        Map<String, Long> counts = orderService.countByStatus(user.getUsername());
        long all = 0;
        for (long n : counts.values()) {
            all += n;
        }
        req.setAttribute("orders", orderService.findByUserAndStatus(user.getUsername(), filter == null ? null : filter.getCode()));
        req.setAttribute("statuses", OrderStatus_24162113.values());
        req.setAttribute("counts", counts);
        req.setAttribute("allCount", all);
        req.setAttribute("currentStatus", filter == null ? "" : filter.getCode());
        req.setAttribute("currentStatusText", filter == null ? "" : filter.getText());
        req.getRequestDispatcher("/WEB-INF/views/user/orders.jsp").forward(req, resp);
    }
}
