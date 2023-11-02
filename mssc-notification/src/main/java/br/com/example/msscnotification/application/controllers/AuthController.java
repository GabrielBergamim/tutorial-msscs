package br.com.example.msscnotification.application.controllers;

import br.com.example.msscnotification.domain.dto.login.LoginInputDto;
import br.com.example.msscnotification.domain.model.AuthorizationToken;
import br.com.example.msscnotification.infrastructure.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public AuthorizationToken login(@RequestBody LoginInputDto loginInputDto) {
        return authService.login(loginInputDto);
    }
}
