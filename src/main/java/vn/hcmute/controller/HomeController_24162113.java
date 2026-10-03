package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import vn.hcmute.service.IVideoService_24162113;
import vn.hcmute.service.VideoServiceImpl_24162113;

@WebServlet(urlPatterns = {"", "/home"})
public class HomeController_24162113 extends HttpServlet {
    private static final int PAGE_SIZE = 3;
    private final IVideoService_24162113 videoService = new VideoServiceImpl_24162113();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, Integer> pages = new HashMap<>();
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            if (entry.getKey().matches("p\\d{1,9}")) {
                try {
                    pages.put(Integer.parseInt(entry.getKey().substring(1)), Integer.parseInt(entry.getValue()[0]));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        req.setAttribute("blocks", videoService.buildBlocks(pages, PAGE_SIZE));
        req.getRequestDispatcher("/WEB-INF/views/user/home.jsp").forward(req, resp);
    }
}
