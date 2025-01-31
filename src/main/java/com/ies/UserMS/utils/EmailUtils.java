package com.ies.UserMS.utils;

import com.ies.UserMS.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendMailToSendTempPassword(String to, String password) throws Exception {
        String subject = "IES - Account Created";
        String body = "Your account has been created. Your temporary password is: "+password;
        return sendMail(to, subject, body);
    }
    public boolean sendMailToRecoverPassword(String to, String password) throws Exception {
        String subject = "IES - Account Password Recovery Mail";
        String body = "Your password is: "+password;
        return sendMail(to, subject, body);
    }

    public boolean sendMail(String to, String subject, String body) throws Exception {
        boolean isSent = false;
        System.out.println("Sending mail to: "+to);
        try {
             MimeMessage message = mailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(message);
             helper.setTo(to);
             helper.setSubject(subject);
             helper.setText(body, true);
             mailSender.send(message);
             System.out.println("Mail sent successfully");
             isSent = true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return isSent;
    }
}
