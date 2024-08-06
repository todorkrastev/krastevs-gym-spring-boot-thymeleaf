package com.todorkrastev.krastevsgym.web;


import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.entity.UserRoleEntity;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.impl.KrastevsGymUserDetailsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KrastevsGymUserDetailsService krastevsGymUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserEntity user;

    private Long userId;


    @BeforeEach
    public void setUp() {
        user = new UserEntity()
                .setId(10000L)
                .setUuid(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .setFirstName("Florian")
                .setLastName("Dewitz")
                .setAge(30)
                .setWeight(80)
                .setHeight(180)
                .setEmail("floriandewitz@web.de")
                .setPassword(passwordEncoder.encode("topsecret"))
                .setRoles(Set.of(new UserRoleEntity().setId(1L).setRole(UserRoleEnum.USER)))
                .setExercises(List.of());


        userId = userRepository.save(user).getId();

        krastevsGymUserDetailsService = new KrastevsGymUserDetailsService(userRepository);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteById(userId);
    }

    @Test
    void testHome_userLoggedIn() throws Exception {
        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/")
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("welcomeMessage"))
                .andExpect(model().attribute("welcomeMessage", krastevsGymUserDetails.getFullName()));
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("welcomeMessage"))
                .andExpect(model().attribute("welcomeMessage", "Anonymous"));
    }
}