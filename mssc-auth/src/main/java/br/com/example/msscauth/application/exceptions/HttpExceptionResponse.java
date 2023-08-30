package br.com.example.msscauth.application.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
public class HttpExceptionResponse {

    private final HttpStatus status;
    private final String message;
    private final LocalDateTime timestamp;
}
