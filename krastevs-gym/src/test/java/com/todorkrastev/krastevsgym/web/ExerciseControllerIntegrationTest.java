package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.impl.KrastevsGymUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ExerciseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KrastevsGymUserDetailsService krastevsGymUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
//    @WithMockUser(username = "user", password = "topsecret")
    void testExerciseDetails() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;
        mockMvc.perform(get("/exercises/exercises-by-category/exercise/{id}", 1L)
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercise"))
                .andExpect(model().attributeExists("exerciseDetails"))
                .andExpect(model().attributeExists("isCreator"))
                .andExpect(model().attribute("isCreator", true))
                .andExpect(model().size(4));
    }

    @Test
    void testExerciseDetails_IsNotCreator() throws Exception {
        UserEntity user = userRepository.findById(3L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;
        mockMvc.perform(get("/exercises/exercises-by-category/exercise/{id}", 1L)
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercise"))
                .andExpect(model().attributeExists("exerciseDetails"))
                .andExpect(model().attributeExists("isCreator"))
                .andExpect(model().attribute("isCreator", false))
                .andExpect(model().size(4));
    }

    @Test
    void testEditExercise() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/exercises/exercises-by-category/exercise/{id}/edit", 1L)
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercise-edit"))
                .andExpect(model().attributeExists("exerciseDetailsDTO"))
                .andExpect(model().size(3));
    }

    @Test
    void testEditExercise_IsNotTheCreator() throws Exception {
        UserEntity user = userRepository.findById(3L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/exercises/exercises-by-category/exercise/{id}/edit", 1L)
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isUnauthorized());
    }

    @Test
//    @WithMockUser(username = "admin", password = "topsecret", roles = {"ADMIN"})
    void testEditExercise_PUT() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(put("/exercises/exercises-by-category/exercise/{id}/edit", 3L)
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("name", "Updated Name")
                        .param("description", "Updated Description")
                        .param("gifUrl", "Updated Gif Url")
                        .param("musclesWorkedUrl", "Updated Muscles Worked Url")
                        .param("instructions", "Updated Instructions")
                        .param("notes", "Updated Notes")
                        .param("equipmentType", EquipmentTypeEnum.BARBELL.name())
                        .param("category", ExerciseCategoryEnum.ABS.name())
                        .param("creatorId", user.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/3"));


    }

    @Test
//    @WithMockUser(username = "admin", password = "topsecret", roles = {"ADMIN"})
    void testEditExercise_PUT_BindingResultHasErrors() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(put("/exercises/exercises-by-category/exercise/{id}/edit", 3L)
                        .with(csrf())
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/edit/error"))
                .andExpect(flash().attributeExists("exerciseDetailsDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.exerciseDetailsDTO"))
                .andExpect(flash().attributeCount(2));
    }

    @Test
    void testEditExerciseError() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/exercises/exercises-by-category/exercise/edit/error")
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercise-edit"))
                .andExpect(model().attributeExists("exerciseDetailsDTO"))
                .andExpect(model().size(3));
    }

    @Test
    void testDeleteExercise() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(delete("/exercises/exercises-by-category/exercise/{id}", 2L)
                        .with(csrf())
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/1"));
    }

    @Test
    void testCreateExercise() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(get("/exercises/exercises-by-category/exercise/create")
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercise-create"))
                .andExpect(model().attributeExists("createExerciseDTO"))
                .andExpect(model().size(3));
    }

    @Test
    void testCreateExercise_POST() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(post("/exercises/exercises-by-category/exercise/create")
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("name", "New Name")
                        .param("description", "New Description")
                        .param("gifUrl", "New Gif Url")
                        .param("musclesWorkedUrl", "New Muscles Worked Url")
                        .param("instructions", "New Instructions")
                        .param("notes", "New Notes")
                        .param("equipmentType", EquipmentTypeEnum.BARBELL.name())
                        .param("category", ExerciseCategoryEnum.ABS.name())
                        .param("creatorId", user.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/" + exerciseRepository.count()));
    }

    @Test
    void testCreateExercise_POST_BingingResultHasErrors() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(post("/exercises/exercises-by-category/exercise/create")
                        .with(csrf())
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/create"))
                .andExpect(flash().attributeExists("createExerciseDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.createExerciseDTO"))
                .andExpect(flash().attributeCount(2));
    }

    @Test
    void testCreateExerciseNotes() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(post("/exercises/exercises-by-category/exercise/{id}/exercise-notes/create", 1L)
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("notes", "New Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/1"));
    }

    @Test
    void testEditExerciseNotes() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

        mockMvc.perform(post("/exercises/exercises-by-category/exercise/{id}/exercise-notes/create", 6L)
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("notes", "New Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/6"));

        mockMvc.perform(put("/exercises/exercises-by-category/exercise/{id}/exercise-notes/edit", 6L)
                        .with(csrf())
                        .with(user(krastevsGymUserDetails))
                        .param("notes", "Updated Notes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/exercises/exercises-by-category/exercise/6"));
    }
}