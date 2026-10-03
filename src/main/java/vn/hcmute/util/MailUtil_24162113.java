package vn.hcmute.util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailUtil_24162113 {
    private static final Properties CONFIG = new Properties();

    static {
        try (InputStream in = MailUtil_24162113.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (in != null) {
                CONFIG.load(in);
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private MailUtil_24162113() {
    }

    public static int otpExpireMinutes() {
        return StringUtil_24162113.toInt(CONFIG.getProperty("otp.expire.minutes"), 5);
    }

    public static void sendOtp(String to, String otp) throws Exception {
        final String username = CONFIG.getProperty("mail.username");
        final String password = CONFIG.getProperty("mail.password");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", CONFIG.getProperty("mail.host", "smtp.gmail.com"));
        props.put("mail.smtp.port", CONFIG.getProperty("mail.port", "587"));
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Mã OTP kích hoạt tài khoản", "UTF-8");
        message.setText("Mã OTP của bạn là: " + otp + ". Mã có hiệu lực trong " + otpExpireMinutes() + " phút.", "UTF-8");
        Transport.send(message);
    }
}
