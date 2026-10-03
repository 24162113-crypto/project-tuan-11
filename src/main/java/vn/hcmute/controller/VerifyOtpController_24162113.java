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
import vn.hcmute.util.OtpUtil_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet("/verify-otp")
public class VerifyOtpController_24162113 extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/auth/verify-otp.jsp";
    private final IUserService_24162113 userService = new UserServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(OtpUtil_24162113.USERNAME) == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(OtpUtil_24162113.USERNAME) == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }
        String username = (String) session.getAttribute(OtpUtil_24162113.USERNAME);
        if ("resend".equals(req.getParameter("action"))) {
            User_24162113 user = userService.findById(username);
            if (user != null) {
                OtpUtil_24162113.issue(session, user);
                req.setAttribute("info", "Đã gửi lại mã OTP tới email của bạn");
            }
            req.getRequestDispatcher(VIEW).forward(req, resp);
            return;
        }
        String code = StringUtil_24162113.clean(req.getParameter("otp"));
        Long expire = (Long) session.getAttribute(OtpUtil_24162113.EXPIRE);
        if (expire == null || System.currentTimeMillis() > expire) {
            req.setAttribute("error", "Mã OTP đã hết hạn, vui lòng gửi lại mã mới");
        } else if (!code.equals(session.getAttribute(OtpUtil_24162113.CODE))) {
            req.setAttribute("error", "Mã OTP không đúng");
        } else {
            userService.activate(username);
            OtpUtil_24162113.clear(session);
            resp.sendRedirect(req.getContextPath() + "/login?registered=1");
            return;
        }
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }
}
