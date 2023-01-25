package br.com.example.msscauth.application.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class UnauthorizedException extends HttpException {

    public UnauthorizedException(String title, String message) {
        super(HttpStatus.UNAUTHORIZED, title, message, LocalDateTime.now());
    }
}
