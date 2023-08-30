package br.com.example.msscauth.infrastructure.config.exceptions;

import br.com.example.msscauth.application.exceptions.HttpExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHttpHandler extends ResponseEntityExceptionHandler {

    private final Map<Class<? extends Exception>, HttpStatus> exceptionToStatusCode;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<HttpExceptionResponse> handleException(Exception ex) {
        HttpExceptionResponse exception = HttpExceptionResponse.builder()
                .message(ex.getMessage())
                .status(exceptionToStatusCode.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR))
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exception, exception.getStatus());
    }
}
