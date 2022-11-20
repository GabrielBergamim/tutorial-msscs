package br.com.example.msscusers.infrastructure.producers;

import br.com.example.msscusers.domain.models.Notification;
import br.com.example.msscusers.domain.usecases.usernotification.UserNotification;
import br.com.example.msscusers.infrastructure.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNotificationImpl implements UserNotification {

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(Notification notification) {
        jmsTemplate.convertAndSend(JmsConfig.EMAIL_NOTIFICATION, notification);
    }
}
