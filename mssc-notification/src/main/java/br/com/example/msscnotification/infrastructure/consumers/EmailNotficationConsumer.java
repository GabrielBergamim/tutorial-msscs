package br.com.example.msscnotification.infrastructure.consumers;

import br.com.example.msscnotification.domain.model.Notification;
import br.com.example.msscnotification.infrastructure.services.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotficationConsumer {

    private final EmailServiceImpl emailService;

    @RabbitListener(queues = {"${queue.name}"})
    public void listen(@Payload Notification notificationMessage){
        System.out.println("I Got a Message!!!!!");

        System.out.println(notificationMessage);

        emailService.sendEmail(notificationMessage);
    }
}
