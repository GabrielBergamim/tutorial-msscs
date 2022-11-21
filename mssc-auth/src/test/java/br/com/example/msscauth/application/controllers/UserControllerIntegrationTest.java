package br.com.example.msscauth.application.controllers;

import br.com.example.msscauth.application.exceptions.BadRequestException;
import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private UserInputDto user;

    @BeforeEach
    void setUp() {
        user = UserInputDto.builder()
                .email("test@email.com")
                .password("123456789")
                .passwordConfirmation("123456789")
                .build();
    }

    @Test
    @Order(1)
    void shouldReturnBadRequestException_whenUserInputEmailIsEmpty() {
        user.setEmail(null);
        sendRequestForValidatingErrors();
    }

    @Test
    @Order(2)
    void shouldReturnBadRequestException_whenUserInputPasswordIsEmpty() {
        user.setPassword(null);
        sendRequestForValidatingErrors();
    }

    @Test
    @Order(3)
    void shouldReturnBadRequestException_whenUserInputPasswordConfirmationIsEmpty() {
        user.setPasswordConfirmation(null);
        sendRequestForValidatingErrors();
    }

    @Test
    @Order(5)
    void shouldReturnBadRequestException_whenUserInputEmailAlreadyExists() {
        sendRequestForValidatingErrors();
    }

    @Test
    @Order(4)
    void shouldReturnCreatedUserWithId_whenParameterUserIsInformed() {
        var responseEntity = testRestTemplate
                .postForEntity("/api/user", user, UserOutputDto.class);
        var userCreated = Optional.ofNullable(responseEntity.getBody())
                .orElse(new UserOutputDto());

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertTrue(userCreated.getId() > 0);
        Assertions.assertEquals(user.getEmail(), userCreated.getEmail());
    }

    private void sendRequestForValidatingErrors() {
        var responseEntity = testRestTemplate
                .postForEntity("/api/user", user, BadRequestException.class);
        var reponseError = responseEntity.getBody();
        validateResponseError(responseEntity, reponseError);
    }

    private void validateResponseError(ResponseEntity<BadRequestException> responseEntity,
                                       BadRequestException reponseError) {
        Assertions.assertNotNull(reponseError);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, reponseError.getStatus());
        Assertions.assertNotNull(reponseError.getTitle());
        Assertions.assertNotNull(reponseError.getMessage());
    }
}
