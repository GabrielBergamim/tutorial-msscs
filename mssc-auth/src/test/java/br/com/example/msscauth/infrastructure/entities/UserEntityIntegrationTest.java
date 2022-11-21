package br.com.example.msscauth.infrastructure.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.PersistenceException;

@DataJpaTest
public class UserEntityIntegrationTest {

    UserEntity sut;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setup() {
        sut = UserEntity.builder()
                .email("test@test.com")
                .password("12345678")
                .build();
    }

    @Test
    void shouldReturnStoredUserDetails_whenValidUserDetailsProvided() {
        UserEntity storedUserEntity = testEntityManager.persistAndFlush(sut);

        Assertions.assertTrue(storedUserEntity.getId() > 0);
        Assertions.assertEquals(sut.getEmail(), storedUserEntity.getEmail());
        Assertions.assertEquals(sut.getPassword(), storedUserEntity.getPassword());
    }

    @Test
    void shouldThrowException_whenEmailIsTooLong() {
        sut.setEmail("123456789012345678901234567890123456789012345678901234567890");

        Assertions.assertThrows(PersistenceException.class, () ->
                testEntityManager.persistAndFlush(sut)
        );
    }

    @Test
    void shouldThrowException_whenEmailIsNull() {
        sut.setEmail(null);

        Assertions.assertThrows(PersistenceException.class, () ->
                testEntityManager.persistAndFlush(sut)
        );
    }

    @Test
    void shouldThrowException_whenPasswordIsNull() {
        sut.setPassword(null);

        Assertions.assertThrows(PersistenceException.class, () ->
                testEntityManager.persistAndFlush(sut)
        );
    }

    @Test
    void shouldThrowException_whenEmailIsNotUnique() {
        testEntityManager.persistAndFlush(sut);

        UserEntity user = UserEntity.builder()
                .email("test@test.com")
                .password("12345678")
                .build();

        Assertions.assertThrows(PersistenceException.class, () ->
                testEntityManager.persistAndFlush(user)
        );
    }
}
