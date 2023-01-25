package br.com.example.msscauth.infrastructure.config.exceptions;

import br.com.example.msscauth.application.exceptions.BadRequestException;
import br.com.example.msscauth.application.exceptions.UnauthorizedException;
import br.com.example.msscauth.domain.exceptions.InvalidLoginException;
import br.com.example.msscauth.domain.exceptions.MissingParameterException;
import br.com.example.msscauth.domain.exceptions.UserValidationException;
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

    @ExceptionHandler({ MissingParameterException.class })
    public ResponseEntity<BadRequestException> handleMissingParameterBadRequest(Exception ex, WebRequest request) {
        BadRequestException badRequestException = new BadRequestException("Invalid parameters", ex.getMessage());
        return new ResponseEntity<>(badRequestException, badRequestException.getStatus());
    }

    @ExceptionHandler({ InvalidLoginException.class })
    public ResponseEntity<UnauthorizedException> handleInvalidLogin(Exception ex, WebRequest request) {
        UnauthorizedException badRequestException = new UnauthorizedException("Unauthorized", ex.getMessage());
        return new ResponseEntity<>(badRequestException, badRequestException.getStatus());
    }

}
