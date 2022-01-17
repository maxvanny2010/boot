package com.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.*;

/**
 * MailConfig.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/4/2020
 */
@Configuration
public class MailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${mail.debug}")
    private String debug;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String enable;


    @Bean
    public JavaMailSender getJavaMailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        Properties prop = mailSender.getJavaMailProperties();
        prop.setProperty("mail.transport.protocol", this.protocol);
        prop.setProperty("mail.debug", this.debug);
        prop.setProperty("mail.smtp.auth", this.auth);
        prop.setProperty("mail.smtp.starttls.enable", this.enable);

        return mailSender;
    }
}
