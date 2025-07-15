package com.agstechcloud.birthdayjob.service;


import com.agstechcloud.birthdayjob.entity.EmailCredentialEntity;
import com.agstechcloud.birthdayjob.entity.Employee;
import com.agstechcloud.birthdayjob.entity.Wish;
import com.agstechcloud.birthdayjob.repository.EmailCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private EmailCredentialRepository emailConfigRepository;

    public void sendBirthdayEmail(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("Happy Birthday, " + employee.getFirstName() + "!");
        message.setText("Dear " + employee.getFirstName() + ",\n\n" +
                "Wishing you a very Happy Birthday! Thank you for being a valuable part of our team.\n\n" +
                "Best wishes,\nCompany Team");
        javaMailSender.send(message);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("atulstpt@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("agscloudtechnologies@gmail.com");
        javaMailSender.send(message);
    }

    public void sendWishEmail(Employee employee) {
        Wish wish = new Wish();
        try {
            // Fetch active email configuration
            EmailCredentialEntity emailConfig = emailConfigRepository.findByIsActiveTrue();
            if (emailConfig == null) {
                throw new RuntimeException("No active email configuration found");
            }

            // Configure JavaMailSender dynamically
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(emailConfig.getSmtpHost());
            mailSender.setPort(emailConfig.getSmtpPort());
            mailSender.setUsername(emailConfig.getSmtpUsername());
            mailSender.setPassword(emailConfig.getSmtpPassword());

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            // Prepare email content
            Context context = new Context();
            context.setVariable("firstName", employee.getFirstName());
            context.setVariable("lastName", employee.getLastName());
            //context.setVariable("wishType", wish.getWishType().substring(0, 1).toUpperCase() + wish.getWishType().substring(1));
            //context.setVariable("message", wish.getMessage());
            context.setVariable("senderName", emailConfig.getSenderName());
            context.setVariable("senderEmail", emailConfig.getSenderEmail());

            String emailContent = templateEngine.process("email_template", context);

            // Create and send email
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(emailConfig.getSenderEmail(), emailConfig.getSenderName());
            helper.setTo(employee.getEmail());
            //helper.setSubject(wish.getWishType().substring(0, 1).toUpperCase() + wish.getWishType().substring(1) + " Wishes!");
            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);

            wish.setStatus(Wish.Status.SENT);
        } catch (Exception e) {
            wish.setStatus(Wish.Status.FAILED);
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}
