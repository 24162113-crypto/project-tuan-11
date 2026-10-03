package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import vn.hcmute.entity.User_24162113;
import vn.hcmute.service.IUserService_24162113;
import vn.hcmute.service.UserServiceImpl_24162113;
import vn.hcmute.util.OtpUtil_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet("/register")
public class RegisterController_24162113 extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/auth/register.jsp";
    private final IUserService_24162113 userService = new UserServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User_24162113 user = new User_24162113();
        user.setUsername(StringUtil_24162113.clean(req.getParameter("username")));
        user.setFullname(StringUtil_24162113.clean(req.getParameter("fullname")));
        user.setEmail(StringUtil_24162113.clean(req.getParameter("email")));
        user.setPhone(StringUtil_24162113.clean(req.getParameter("phone")));
        String password = req.getParameter("password") == null ? "" : req.getParameter("password");
        String confirm = req.getParameter("confirm") == null ? "" : req.getParameter("confirm");
        user.setPassword(password);

        String error = null;
        if (!user.getUsername().matches("[A-Za-z0-9_]{3,50}")) {
            error = "Tên đăng nhập gồm 3-50 ký tự chữ, số hoặc dấu gạch dưới";
        } else if (password.length() < 6 || password.length() > 50) {
            error = "Mật khẩu phải từ 6 đến 50 ký tự";
        } else if (!password.equals(confirm)) {
            error = "Mật khẩu xác nhận không khớp";
        } else if (user.getFullname().isEmpty() || user.getFullname().length() > 50) {
            error = "Họ tên không hợp lệ";
        } else if (!user.getEmail().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+") || user.getEmail().length() > 150) {
            error = "Email không hợp lệ";
        } else if (user.getPhone().length() > 15) {
            error = "Số điện thoại không hợp lệ";
        } else {
            error = userService.register(user);
        }

        if (error != null) {
            user.setPassword(null);
            req.setAttribute("error", error);
            req.setAttribute("form", user);
            req.getRequestDispatcher(VIEW).forward(req, resp);
            return;
        }
        OtpUtil_24162113.issue(req.getSession(true), user);
        resp.sendRedirect(req.getContextPath() + "/verify-otp");
    }
}
