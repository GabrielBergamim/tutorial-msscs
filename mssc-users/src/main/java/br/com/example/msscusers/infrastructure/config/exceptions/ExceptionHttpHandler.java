package br.com.example.msscusers.infrastructure.config.exceptions;

import br.com.example.msscusers.application.exceptions.BadRequestException;
import br.com.example.msscusers.domain.exceptions.UserValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHttpHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserValidationException.class })
    public ResponseEntity<BadRequestException> handleUserValidationBadRequest(Exception ex, WebRequest request) {
        BadRequestException badRequestException = new BadRequestException("Create user", ex.getMessage());
        return new ResponseEntity<>(badRequestException, badRequestException.getStatus());
    }
}
