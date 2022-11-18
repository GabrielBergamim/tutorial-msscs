package br.com.example.msscusers.application.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BadRequestException extends HttpException {

    public BadRequestException(String title, String message) {
        super(HttpStatus.BAD_REQUEST, title, message, LocalDateTime.now());
    }
}
