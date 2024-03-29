package br.com.example.msscauth.application.controllers;

import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;
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

    @PostMapping
    public ResponseEntity<UserOutputDto> createUser(@RequestBody UserInputDto userInputDto) {
        UserOutputDto createdUser = createUserUseCase.execute(userInputDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
