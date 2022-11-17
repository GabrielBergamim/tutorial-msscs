package br.com.example.msscusers.infrastructure.repositories;

import br.com.example.msscusers.domain.models.User;
import br.com.example.msscusers.infrastructure.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryImplIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserJpaRepository userJPARepository;

    private UserRepositoryImpl sut;

    private User user;

    @BeforeEach
    void setUp() {
        sut = new UserRepositoryImpl(userJPARepository, new ModelMapper());

        user = User.builder()
                .email("test@email.com")
                .password("123456789")
                .build();
    }

    @Test
    void shouldReturnUserWithId_whenSavesInDB() {
        User userCreated = sut.save(user);
        Assertions.assertTrue(userCreated.getId() > 0);
    }

    @Test
    void shouldReturnUserWithEmail_whenCallsFindByEmail() {
        UserEntity userEntity = UserEntity.builder()
                .email("test@find.com")
                .password("1234567")
                .build();

        testEntityManager.persistAndFlush(userEntity);

        Optional<User> optionalUser = sut.findByEmail(userEntity.getEmail());

        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(userEntity.getEmail(), optionalUser.map(User::getEmail).orElse(""));
    }
}
