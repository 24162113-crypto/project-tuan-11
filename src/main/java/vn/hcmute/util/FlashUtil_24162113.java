package vn.hcmute.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FlashUtil_24162113 {
    private static final String OK = "flashOk";
    private static final String ERROR = "flashError";

    private FlashUtil_24162113() {
    }

    public static void ok(HttpSession session, String message) {
        session.setAttribute(OK, message);
    }

    public static void error(HttpSession session, String message) {
        session.setAttribute(ERROR, message);
    }

    public static void consume(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return;
        }
        for (String key : new String[] {OK, ERROR}) {
            Object value = session.getAttribute(key);
            if (value != null) {
                req.setAttribute(key, value);
                session.removeAttribute(key);
            }
        }
    }
}
