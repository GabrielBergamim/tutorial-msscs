package br.com.example.msscnotification.infrastructure.services;

import br.com.example.msscnotification.domain.model.Notification;
import br.com.example.msscnotification.domain.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void sendEmail(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(notification.getTo());
        message.setSubject(notification.getSubject());
        message.setText(notification.getMessage());

        emailSender.send(message);
    }
}
