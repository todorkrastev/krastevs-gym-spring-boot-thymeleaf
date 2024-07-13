package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.UserRegisterDTO;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserServiceImpl toTest;

    @Mock
    private UserRepository mockRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                new ModelMapper(),
                mockPasswordEncoder,
                mockRepository
        );
    }

    @Test
    void registerUser() {
        UserRegisterDTO userRegisterDTO =
                new UserRegisterDTO()
                        .setEmail("tdk@mail.com")
                        .setFirstName("Todor")
                        .setLastName("Krastev")
                        .setPassword("top-secret");

        toTest.registerUser(userRegisterDTO);
    }
}