package com.example.nexus.config;

    import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
    import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

    @Configuration
    public class MailConfiguration {

        @Value("${spring.mail.host}")
        String host;
        @Value("${spring.mail.port}")
        String port;
        @Value("${spring.mail.username}")
        String username;
        @Value("${spring.mail.password}")
        String password;

        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(host);
            mailSender.setPort(Integer.valueOf(port));

            mailSender.setUsername(username);
            mailSender.setPassword(password);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            return mailSender;
        }

        @Async
        public void sendEmail(MimeMessage email) {
            getJavaMailSender().send(email);
        }
    }

