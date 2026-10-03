package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import vn.hcmute.dto.VideoInfo_24162113;
import vn.hcmute.service.IVideoService_24162113;
import vn.hcmute.service.VideoServiceImpl_24162113;
import vn.hcmute.util.StringUtil_24162113;

@WebServlet("/video")
public class VideoDetailController_24162113 extends HttpServlet {
    private final IVideoService_24162113 videoService = new VideoServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideoInfo_24162113 video = videoService.viewDetail(StringUtil_24162113.clean(req.getParameter("id")));
        if (video == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("video", video);
        req.getRequestDispatcher("/WEB-INF/views/user/detail.jsp").forward(req, resp);
    }
}
