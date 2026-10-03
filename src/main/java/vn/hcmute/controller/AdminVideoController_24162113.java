package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import vn.hcmute.entity.Category_24162113;
import vn.hcmute.entity.Video_24162113;
import vn.hcmute.service.CategoryServiceImpl_24162113;
import vn.hcmute.service.ICategoryService_24162113;
import vn.hcmute.service.IVideoService_24162113;
import vn.hcmute.service.VideoServiceImpl_24162113;
import vn.hcmute.util.StringUtil_24162113;
import vn.hcmute.util.UploadUtil_24162113;

@WebServlet(urlPatterns = {"/admin/videos", "/admin/videos/add", "/admin/videos/edit", "/admin/videos/delete", "/admin/videos/save"})
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 6 * 1024 * 1024)
public class AdminVideoController_24162113 extends HttpServlet {
    private static final int PAGE_SIZE = 6;
    private final IVideoService_24162113 videoService = new VideoServiceImpl_24162113();
    private final ICategoryService_24162113 categoryService = new CategoryServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/admin/videos/add":
                Video_24162113 blank = new Video_24162113();
                blank.setActive(true);
                blank.setViews(0);
                blank.setPrice(50000L);
                blank.setStock(10);
                showForm(req, resp, blank, "add");
                break;
            case "/admin/videos/edit":
                Video_24162113 video = videoService.findById(StringUtil_24162113.clean(req.getParameter("id")));
                if (video == null) {
                    resp.sendRedirect(req.getContextPath() + "/admin/videos");
                } else {
                    showForm(req, resp, video, "edit");
                }
                break;
            case "/admin/videos":
                req.setAttribute("result", videoService.findPage(StringUtil_24162113.toInt(req.getParameter("page"), 1), PAGE_SIZE));
                req.getRequestDispatcher("/WEB-INF/views/admin/videos.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/admin/videos/delete".equals(req.getServletPath())) {
            videoService.delete(StringUtil_24162113.clean(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/admin/videos?page=" + StringUtil_24162113.toInt(req.getParameter("page"), 1));
            return;
        }
        if (!"/admin/videos/save".equals(req.getServletPath())) {
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
            return;
        }
        String mode = "edit".equals(req.getParameter("mode")) ? "edit" : "add";
        String id = StringUtil_24162113.clean(req.getParameter("videoId"));
        Video_24162113 video;
        if (mode.equals("edit")) {
            video = videoService.findById(id);
            if (video == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/videos");
                return;
            }
        } else {
            video = new Video_24162113();
            video.setVideoId(id);
        }
        video.setTitle(StringUtil_24162113.clean(req.getParameter("title")));
        video.setDescription(StringUtil_24162113.clean(req.getParameter("description")));
        video.setViews(Math.max(0, StringUtil_24162113.toInt(req.getParameter("views"), 0)));
        video.setPrice(Math.max(0L, StringUtil_24162113.toLong(req.getParameter("price"), 0L)));
        video.setStock(Math.max(0, StringUtil_24162113.toInt(req.getParameter("stock"), 0)));
        video.setActive(req.getParameter("active") != null);
        String categoryId = StringUtil_24162113.clean(req.getParameter("categoryId"));
        Category_24162113 category = categoryId.isEmpty() ? null : categoryService.findById(StringUtil_24162113.toInt(categoryId, -1));
        video.setCategory(category);

        String error = null;
        Part part = req.getPart("posterFile");
        if (part != null && part.getSize() > 0) {
            String saved = UploadUtil_24162113.save(part);
            if (saved == null) {
                error = "Poster chỉ hỗ trợ ảnh png, jpg, jpeg, gif hoặc webp";
            } else {
                video.setPoster(saved);
            }
        }
        if (error == null && video.getTitle().isEmpty()) {
            error = "Vui lòng nhập tiêu đề";
        } else if (error == null && video.getTitle().length() > 200) {
            error = "Tiêu đề tối đa 200 ký tự";
        } else if (error == null && video.getDescription().length() > 500) {
            error = "Mô tả tối đa 500 ký tự";
        } else if (error == null && video.getPrice() > 1_000_000_000L) {
            error = "Giá tối đa 1.000.000.000";
        } else if (error == null && video.getStock() > 100000) {
            error = "Tồn kho tối đa 100000";
        } else if (error == null && category == null) {
            error = "Vui lòng chọn danh mục";
        } else if (error == null && mode.equals("add")) {
            if (id.isEmpty()) {
                video.setVideoId("V" + System.currentTimeMillis());
            } else if (id.length() > 50) {
                error = "Mã video tối đa 50 ký tự";
            } else if (videoService.findById(id) != null) {
                error = "Mã video đã tồn tại";
            }
        }
        if (error != null) {
            req.setAttribute("error", error);
            showForm(req, resp, video, mode);
            return;
        }
        videoService.save(video);
        resp.sendRedirect(req.getContextPath() + "/admin/videos");
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Video_24162113 video, String mode) throws ServletException, IOException {
        req.setAttribute("video", video);
        req.setAttribute("mode", mode);
        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher("/WEB-INF/views/admin/video-form.jsp").forward(req, resp);
    }
}
