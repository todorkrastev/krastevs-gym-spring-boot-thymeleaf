package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegister_GET() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegister_POST() throws Exception {
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

        UserEntity userEntity = userEntityOpt.get();

        assertEquals("Florian", userEntity.getFirstName());
        assertEquals("Dewitz", userEntity.getLastName());

        assertTrue(passwordEncoder.matches("topsecret", userEntity.getPassword()));
    }

    @Test
    void testRegister_POST_InvalidData() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("firstName", "Florian")
                        .param("lastName", "Dewitz")
                        .param("email", "floriandewitz@web.de")
                        .param("password", "12345")
                        .param("confirmPassword", "54321")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    @Test
    void testRegister_POST_EmailExists() throws Exception {
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

        mockMvc.perform(post("/users/register")
                        .param("firstName", "Florian")
                        .param("lastName", "Dewitz")
                        .param("email", "floriandewitz@web.de")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }
}