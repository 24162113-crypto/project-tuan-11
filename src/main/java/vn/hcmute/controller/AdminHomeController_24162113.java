package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import vn.hcmute.service.CategoryServiceImpl_24162113;
import vn.hcmute.service.ICategoryService_24162113;
import vn.hcmute.service.IVideoService_24162113;
import vn.hcmute.service.VideoServiceImpl_24162113;

@WebServlet("/admin/home")
public class AdminHomeController_24162113 extends HttpServlet {
    private final IVideoService_24162113 videoService = new VideoServiceImpl_24162113();
    private final ICategoryService_24162113 categoryService = new CategoryServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("totalVideos", videoService.count());
        req.setAttribute("totalCategories", categoryService.findAll().size());
        req.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(req, resp);
    }
}
