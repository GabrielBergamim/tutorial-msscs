package br.com.example.msscusers.application.controllers;

import br.com.example.msscusers.domain.dto.UserInputDto;
import br.com.example.msscusers.domain.dto.UserOutputDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private UserInputDto user;

    @BeforeEach
    void setUp() {
        user = UserInputDto.builder()
                .email("test@email.com")
                .password("123456789")
                .passowrdConfirmation("123456789")
                .build();
    }

    @Test
    void shouldReturnCreatedUserWithId_whenParameterUserIsInformed() {
        var responseEntity = testRestTemplate
                .postForEntity("/api/user", user, UserOutputDto.class);
        var userCreated = Optional.ofNullable(responseEntity.getBody())
                .orElse(new UserOutputDto());

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertTrue(userCreated.getId() > 0);
        Assertions.assertEquals(user.getEmail(), userCreated.getEmail());
    }
}
