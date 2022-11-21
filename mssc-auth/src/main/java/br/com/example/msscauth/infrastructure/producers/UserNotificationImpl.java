package br.com.example.msscauth.infrastructure.producers;

import br.com.example.msscauth.domain.models.Notification;
import br.com.example.msscauth.domain.usecases.usernotification.UserNotification;
import br.com.example.msscauth.infrastructure.config.JmsConfig;
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
