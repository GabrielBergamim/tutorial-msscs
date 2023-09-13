package br.com.example.msscauth.application.controllers;

import br.com.example.msscauth.application.exceptions.HttpExceptionResponse;
import br.com.example.msscauth.domain.dto.user.UserInputDto;
import br.com.example.msscauth.domain.dto.user.UserOutputDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class UserControllerIntegrationTest {

    @Container
    public static GenericContainer rabbit = new GenericContainer("rabbitmq:3-management")
            .withExposedPorts(5672, 15672);

    @DynamicPropertySource
    static void rabbitProperties(DynamicPropertyRegistry registry) {
        rabbit.start();
        registry.add("spring.rabbitmq.host", rabbit::getHost);
        registry.add("spring.rabbitmq.port", rabbit::getFirstMappedPort);
    }

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

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertTrue(userCreated.getId() > 0);
        assertEquals(user.getEmail(), userCreated.getEmail());
    }

    private void sendRequestForValidatingErrors() {
        var responseEntity = testRestTemplate
                .postForEntity("/api/user", user, HttpExceptionResponse.class);
        var reponseError = responseEntity.getBody();
        validateResponseError(responseEntity, reponseError);
    }

    private void validateResponseError(ResponseEntity<HttpExceptionResponse> responseEntity,
                                       HttpExceptionResponse reponseError) {
        assertNotNull(reponseError);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, reponseError.getStatus());
        assertNotNull(reponseError.getMessage());
    }
}
