package vn.hcmute.util;

import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.hcmute.entity.User_24162113;

public class OtpUtil_24162113 {
    public static final String USERNAME = "otpUsername";
    public static final String CODE = "otpCode";
    public static final String EXPIRE = "otpExpire";
    private static final Logger LOGGER = Logger.getLogger(OtpUtil_24162113.class.getName());
    private static final SecureRandom RANDOM = new SecureRandom();

    private OtpUtil_24162113() {
    }

    public static String generate() {
        return String.format("%06d", RANDOM.nextInt(1_000_000));
    }

    public static void issue(HttpSession session, User_24162113 user) {
        String code = generate();
        session.setAttribute(USERNAME, user.getUsername());
        session.setAttribute(CODE, code);
        session.setAttribute(EXPIRE, System.currentTimeMillis() + MailUtil_24162113.otpExpireMinutes() * 60_000L);
        try {
            MailUtil_24162113.sendOtp(user.getEmail(), code);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Không gửi được email OTP tới " + user.getEmail() + ". Mã OTP: " + code, e);
        }
    }

    public static void clear(HttpSession session) {
        session.removeAttribute(USERNAME);
        session.removeAttribute(CODE);
        session.removeAttribute(EXPIRE);
    }
}
