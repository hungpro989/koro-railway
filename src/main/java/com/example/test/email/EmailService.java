package com.example.test.email;

import org.springframework.context.annotation.Bean;
public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
