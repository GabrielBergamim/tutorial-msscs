package br.com.example.msscauth.application.controllers;

import br.com.example.msscauth.domain.dto.login.LoginInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;
import br.com.example.msscauth.domain.models.AuthorizationToken;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthorizationToken> login(@RequestBody LoginInputDto loginInputDto) {
        AuthorizationToken authorizationToken = authUseCase.execute(loginInputDto.getEmail(),
                loginInputDto.getPassword());
        return new ResponseEntity<>(authorizationToken, HttpStatus.CREATED);
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userDetails")
    public ResponseEntity<UserOutputDto> userDetails() {
        return ResponseEntity.ok(UserOutputDto.builder()
                .email("user_email@email.com")
                .id(1L)
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build());
    }
}
