package br.com.example.msscauth.application.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
abstract class HttpException {
    private final HttpStatus status;
    private final String title;
    private final String message;
    private final LocalDateTime timestamp;
}
