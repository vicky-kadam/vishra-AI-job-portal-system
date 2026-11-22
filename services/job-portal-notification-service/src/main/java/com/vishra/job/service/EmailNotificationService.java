package com.vishra.job.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendStatusChangeEmail() throws Exception {
        try{
            String subject = "Application update: ";
            String body = buildStatusChangeEmail();
            String candidateEmail = "";
            sendEmail(candidateEmail,subject,body);

        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }


    private void sendEmail(String candidateEmail, String subject, String body) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setTo(candidateEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body,true);

        javaMailSender.send(mimeMessage);
    }



    private String buildStatusChangeEmail(){
        return "<h1> Application status changed </h1>";
    }


}
