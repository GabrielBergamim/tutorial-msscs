package br.com.example.msscauth.infrastructure.producers;

import br.com.example.msscauth.domain.models.Notification;
import br.com.example.msscauth.domain.usecases.usernotification.UserNotification;
import br.com.example.msscauth.infrastructure.config.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNotificationImpl implements UserNotification {

    private final RabbitTemplate template;

    private final RabbitMQProperties properties;

    @Override
    public void execute(Notification notification) {
        template.convertAndSend(properties.getExchange(), properties.getKey(), notification);
    }
}
