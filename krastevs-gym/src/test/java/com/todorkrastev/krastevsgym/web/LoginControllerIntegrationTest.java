package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testLogin_GET() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testLoginError_POST() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("firstName", "Florian")
                        .param("lastName", "Dewitz")
                        .param("email", "floriandewitz@web.de")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

        Optional<UserEntity> userEntityOpt = userRepository.findByEmail("floriandewitz@web.de");

        assertTrue(userEntityOpt.isPresent());

        mockMvc.perform(post("/users/login-error")
                        .param("email", "floriandewitz@web.de")
                        .param("password", "topsecreT")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }
}