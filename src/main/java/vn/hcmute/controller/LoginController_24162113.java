package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.service.IUserService_24162113;
import vn.hcmute.service.UserServiceImpl_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet("/login")
public class LoginController_24162113 extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/auth/login.jsp";
    private final IUserService_24162113 userService = new UserServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = StringUtil_24162113.clean(req.getParameter("username"));
        String password = req.getParameter("password");
        User_24162113 user = userService.login(username, password);
        if (user == null) {
            req.setAttribute("error", "Sai tên đăng nhập, mật khẩu hoặc tài khoản chưa được kích hoạt");
            req.setAttribute("username", username);
            req.getRequestDispatcher(VIEW).forward(req, resp);
            return;
        }
        HttpSession old = req.getSession(false);
        Object cart = null;
        String afterLogin = null;
        if (old != null) {
            cart = old.getAttribute("cart");
            afterLogin = (String) old.getAttribute("afterLogin");
            old.invalidate();
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("currentUser", user);
        if (cart != null) {
            session.setAttribute("cart", cart);
        }
        String target = Boolean.TRUE.equals(user.getAdmin()) ? "/admin/home" : "/home";
        if (afterLogin != null && afterLogin.startsWith("/") && !afterLogin.startsWith("//") && !Boolean.TRUE.equals(user.getAdmin())) {
            target = afterLogin;
        }
        resp.sendRedirect(req.getContextPath() + target);
    }
}
