package br.com.example.msscnotification.infrastructure.config;

import br.com.example.msscnotification.domain.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;

@Configuration
public class JmsConfig {

    public static final String EMAIL_NOTIFICATION = "email-notification";

    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put(Notification.class.getSimpleName(), Notification.class);
        converter.setTypeIdMappings(typeIdMappings);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
