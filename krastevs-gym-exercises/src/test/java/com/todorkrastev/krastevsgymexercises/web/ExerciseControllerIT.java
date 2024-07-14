package com.todorkrastev.krastevsgymexercises.web;

import com.jayway.jsonpath.JsonPath;
import com.todorkrastev.krastevsgymexercises.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgymexercises.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgymexercises.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgymexercises.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerIT {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        ExerciseEntity actualEntity = exerciseRepository.save(
                new ExerciseEntity()
                        .setName("Name")
                        .setDescription("Description")
                        .setGifUrl("GifUrl")
                        .setMusclesWorkedUrl("MusclesWorkedUrl")
                        .setInstructions("Instructions")
                        .setNotes("Notes")
                        .setExerciseCategoryEnum(ExerciseCategoryEnum.ABS)
                        .setEquipmentTypeEnum(EquipmentTypeEnum.BARBELL)
                        .setPicture("Picture")
        );

        mockMvc.perform(get("/exercises/{id}", actualEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(actualEntity.getId().intValue())))
                .andExpect(jsonPath("$.name", is(actualEntity.getName())))
                .andExpect(jsonPath("$.description", is(actualEntity.getDescription())))
                .andExpect(jsonPath("$.gifUrl", is(actualEntity.getGifUrl())))
                .andExpect(jsonPath("$.musclesWorkedUrl", is(actualEntity.getMusclesWorkedUrl())))
                .andExpect(jsonPath("$.instructions", is(actualEntity.getInstructions())))
                .andExpect(jsonPath("$.notes", is(actualEntity.getNotes())));
    }

    @Test
    public void testCreateOffer() throws Exception {
        MvcResult result = mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Exercise name Test",
                                  "description": "Description for Exercise Test",
                                  "equipmentTypeEnum": "BARBELL",
                                  "exerciseCategoryEnum": "BICEPS",
                                  "instructions": "Instructions for Exercise Test",
                                  "picture": "URL for Exercise Test"
                                }
                                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        int id = JsonPath.read(body, "$.id");

        Optional<ExerciseEntity> createdExericeOptional = exerciseRepository.findById((long) id);

        assertTrue(createdExericeOptional.isPresent());

        ExerciseEntity createdExercise = createdExericeOptional.get();

        assertEquals("Exercise name Test", createdExercise.getName());
        assertEquals("Description for Exercise Test", createdExercise.getDescription());
        assertEquals("BARBELL", createdExercise.getEquipmentTypeEnum().toString());
        assertEquals("BICEPS", createdExercise.getExerciseCategoryEnum().toString());
        assertEquals("Instructions for Exercise Test", createdExercise.getInstructions());
        assertEquals("URL for Exercise Test", createdExercise.getPicture());
    }

    @Test
    public void deleteExerciseById() throws Exception {
        ExerciseEntity actualEntity = exerciseRepository.save(
                new ExerciseEntity()
                        .setName("Name")
                        .setDescription("Description")
                        .setGifUrl("GifUrl")
                        .setMusclesWorkedUrl("MusclesWorkedUrl")
                        .setInstructions("Instructions")
                        .setNotes("Notes")
                        .setExerciseCategoryEnum(ExerciseCategoryEnum.ABS)
                        .setEquipmentTypeEnum(EquipmentTypeEnum.BARBELL)
                        .setPicture("Picture")
        );

        mockMvc.perform(delete("/exercises/{id}", actualEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertTrue(exerciseRepository.findById(actualEntity.getId()).isEmpty());
    }

    @Test
    public void testExerciseNotFound() throws Exception {
        mockMvc
                .perform(get("/exercises/{id}", "100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
