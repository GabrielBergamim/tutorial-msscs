package br.com.example.msscauth.application.controllers;

import br.com.example.msscauth.domain.dto.login.LoginInputDto;
import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;
import br.com.example.msscauth.domain.models.AuthorizationToken;
import br.com.example.msscauth.domain.usecases.auth.AuthUseCase;
import br.com.example.msscauth.domain.usecases.createuser.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    private final AuthUseCase authUseCase;

    @PostMapping
    public ResponseEntity<UserOutputDto> createUser(@RequestBody UserInputDto userInputDto) {
        UserOutputDto createdUser = createUserUseCase.execute(userInputDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorizationToken> login(@RequestBody LoginInputDto loginInputDto) {
        AuthorizationToken authorizationToken = authUseCase.execute(loginInputDto.getEmail(), loginInputDto.getPassword());
        return new ResponseEntity<>(authorizationToken, HttpStatus.CREATED);
    }
}
