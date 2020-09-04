package com.boot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * MailSender.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/4/2020
 */
@Service
public class MailSender {
    private final JavaMailSender mail;
    @Value("{spring.mail.username}")
    private String username;

    public MailSender(@Qualifier("getJavaMailSender") final JavaMailSender mail) {
        this.mail = mail;
    }

    public void send(String emailTo, String subjects, String message) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subjects);
        mailMessage.setText(message);
        this.mail.send(mailMessage);
    }
}
