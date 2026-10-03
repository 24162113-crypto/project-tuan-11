package vn.hcmute.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import vn.hcmute.util.UploadUtil_24162113;

@WebServlet("/image")
public class ImageController_24162113 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            name = Paths.get(name).getFileName().toString();
        } catch (InvalidPathException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Path file = UploadUtil_24162113.directory().resolve(name);
        InputStream source = Files.isRegularFile(file) ? Files.newInputStream(file) : getServletContext().getResourceAsStream("/images/" + name);
        if (source == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String type = getServletContext().getMimeType(name);
        resp.setContentType(type != null ? type : "application/octet-stream");
        try (InputStream in = source; OutputStream out = resp.getOutputStream()) {
            in.transferTo(out);
        }
    }
}
