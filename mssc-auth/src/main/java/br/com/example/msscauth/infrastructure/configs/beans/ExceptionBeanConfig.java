package br.com.example.msscauth.infrastructure.configs.beans;

import br.com.example.msscauth.domain.exceptions.InvalidLoginException;
import br.com.example.msscauth.domain.exceptions.MissingParameterException;
import br.com.example.msscauth.domain.exceptions.UserValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Configuration
public class ExceptionBeanConfig {

    @Bean
    public Map<Class<? extends Exception>, HttpStatus> exceptionToStatusCode() {
        return Map.of(
                MissingParameterException.class, HttpStatus.BAD_REQUEST,
                UserValidationException.class, HttpStatus.BAD_REQUEST,
                InvalidLoginException.class, HttpStatus.UNAUTHORIZED
        );
    }
}
