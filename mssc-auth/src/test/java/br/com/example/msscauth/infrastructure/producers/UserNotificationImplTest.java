package br.com.example.msscauth.infrastructure.producers;

import br.com.example.msscauth.domain.models.Notification;
import br.com.example.msscauth.infrastructure.config.RabbitMQProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserNotificationImplTest {

    @InjectMocks
    private UserNotificationImpl sut;

    @Mock
    private RabbitTemplate template;

    @Mock
    private RabbitMQProperties properties;

    @Test
    void shouldExecuteNotificationCallingTemplate() {
        when(properties.getKey()).thenReturn("key");
        when(properties.getExchange()).thenReturn("exchange");

        sut.execute(Notification.builder().build());

        verify(template).convertAndSend(any(String.class), any(String.class), any(Notification.class));
    }
}