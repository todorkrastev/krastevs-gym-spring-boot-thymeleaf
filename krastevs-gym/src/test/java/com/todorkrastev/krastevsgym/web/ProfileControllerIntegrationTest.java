package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.dto.UserInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.service.UserService;
import com.todorkrastev.krastevsgym.service.impl.KrastevsGymUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private KrastevsGymUserDetailsService krastevsGymUserDetailsService;

    @Test
    void testGetProfile() throws Exception {
        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/profile")
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("userInfoDTO"))
                .andExpect(model().size(1));
    }

    @Test
    void testEditProfile() throws Exception {
        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/profile/edit")
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("profile-edit"))
                .andExpect(model().attributeExists("userInfoDTO"))
                .andExpect(model().size(1));
    }

    @Test
    void editProfile_emailHasChanged() throws Exception {
        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(put("/profile/edit")
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("firstName", "Todor")
                        .param("lastName", "Krastev")
                        .param("email", "tdk@web.de"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(model().size(0));
    }

    @Test
    void editProfile_emailHasNotChanged() throws Exception {
        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(put("/profile/edit")
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("firstName", "Todor")
                        .param("lastName", "Krastev")
                        .param("email", "todorkrastev@web.de"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"))
                .andExpect(model().size(0));
    }

    @Test
    void editProfile_bindingResultHasErrors() throws Exception {
        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(put("/profile/edit")
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "todorkrastev@web.de"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/edit/error"))
                .andExpect(flash().attributeExists("userInfoDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.userInfoDTO"))
                .andExpect(flash().attributeCount(2))
                .andExpect(model().size(0));
    }

    @Test
    void testEditProfileError_modelDoesNotContainsAttribute() throws Exception {
        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/profile/edit/error")
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/edit"))
                .andExpect(model().size(0));
    }

    @Test
    void testEditProfileError_modelContainsAttribute() throws Exception {
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        UserEntity user = userService.findUserById(1L);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/profile/edit/error")
                        .with(user(krastevsGymUserDetails))
                        .flashAttr("userInfoDTO", userInfoDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("profile-edit"))
                .andExpect(model().attributeExists("userInfoDTO"));
    }


}