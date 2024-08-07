package com.todorkrastev.krastevsgym.web;

import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.model.user.KrastevsGymUserDetails;
import com.todorkrastev.krastevsgym.repository.UserRepository;
import com.todorkrastev.krastevsgym.service.EquipmentTypeService;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import com.todorkrastev.krastevsgym.service.impl.KrastevsGymUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ExerciseCategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KrastevsGymUserDetailsService krastevsGymUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseCategoryService exerciseCategoryService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @BeforeEach
    public void setUp() {
        krastevsGymUserDetailsService = new KrastevsGymUserDetailsService(userRepository);
    }

    @Test
    @WithMockUser(username = "user", password = "topsecret")
    void testAllCategories() throws Exception {
//        List<ExerciseCategoryInfoDTO> categories = exerciseCategoryService.getAllCategories();

        mockMvc.perform(get("/exercises/exercises-by-category"))
                .andExpect(status().isOk())
                .andExpect(view().name("exercises"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().size(1));
    }

    @Test
//    @WithMockUser(username = "user", password = "topsecret")
    void testExercisesByCategoryId() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);
//        Long userId = user.getId();

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

//        List<ExerciseShortInfoDTO> exercises = exerciseService.getExercisesByCategoryIdAndUserId(1L, userId);
//        List<ExerciseCategoryInfoDTO> categories = exerciseCategoryService.getAllCategories();
//        List<EquipmentTypeDTO> types = equipmentTypeService.getAllTypes();

        mockMvc.perform(get("/exercises/exercises-by-category/{id}", 1)
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercises-by-category"))
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().size(3));
    }

    @Test
//    @WithMockUser(username = "user", password = "topsecret")
    void testExercisesByType() throws Exception {
        UserEntity user = userRepository.findById(1L).orElse(null);
        assertNotNull(user);
//        Long userId = user.getId();

        UserDetails userDetails = krastevsGymUserDetailsService.loadUserByUsername(user.getEmail());
        assertInstanceOf(KrastevsGymUserDetails.class, userDetails);

        KrastevsGymUserDetails krastevsGymUserDetails = (KrastevsGymUserDetails) userDetails;

//        List<ExerciseShortInfoDTO> exercises = exerciseService.getExercisesByTypeAndUserId(1L, userId, 2L);
//        List<ExerciseCategoryInfoDTO> categories = exerciseCategoryService.getAllCategories();
//        List<EquipmentTypeDTO> types = equipmentTypeService.getAllTypes();

        mockMvc.perform(get("/exercises/exercises-by-category/{categoryId}/exercises-by-type/{typeId}", 1, 2)
                        .with(user(krastevsGymUserDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("exercises-by-category"))
                .andExpect(model().attributeExists("exercises"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().size(3));
    }
}