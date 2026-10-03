package vn.hcmute.util;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadUtil_24162113 {
    private UploadUtil_24162113() {
    }

    public static Path directory() throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), "ltweb_uploads_24162113");
        Files.createDirectories(path);
        return path;
    }

    public static String save(Part part) throws IOException {
        String submitted = part.getSubmittedFileName();
        int dot = submitted == null ? -1 : submitted.lastIndexOf('.');
        String extension = dot >= 0 ? submitted.substring(dot).toLowerCase() : "";
        if (!extension.matches("\\.(png|jpg|jpeg|gif|webp)")) {
            return null;
        }
        String name = System.currentTimeMillis() + extension;
        try (InputStream in = part.getInputStream()) {
            Files.copy(in, directory().resolve(name), StandardCopyOption.REPLACE_EXISTING);
        }
        return name;
    }
}
