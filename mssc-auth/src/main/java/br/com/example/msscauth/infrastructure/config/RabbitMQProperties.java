package br.com.example.msscauth.infrastructure.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMQProperties {

    @Value("${queue.name}")
    private String queue;

    @Value("${queue.exchange}")
    private String exchange;

    @Value("${queue.key}")
    private String key;
}
