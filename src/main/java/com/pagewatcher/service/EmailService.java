package com.pagewatcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String email, String url) throws Exception {
        String body = "<h2>Hello Darly</h2>" +
                "<br/><p>Puede ser que ya abrieron los examanes !!! Go, go, go</p>" +
                "<br/><p>Para que llegues mas rapido ....</p>" +
                "<br/>\n<a href=" + url + "> Click me</a>" +
                "<br/> :)";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Algo cambio en la pagina de NDEB !!");
        helper.setText(body, true);

        helper.setSentDate(new Date());
        emailSender.send(message);
        LOGGER.info("Mail sent to ==> " + email);
    }

    public static void main(String[] args) throws NoSuchFieldException {
        EmailService e = new EmailService();
        System.out.println(e.getClass().getDeclaredField("mailSender"));
    }
}
