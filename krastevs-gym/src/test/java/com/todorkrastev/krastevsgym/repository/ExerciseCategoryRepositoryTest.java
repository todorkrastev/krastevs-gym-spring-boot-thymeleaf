package com.todorkrastev.krastevsgym.repository;

import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// to test this class, you need to comment the columnDefinition = "TEXT" in ExerciseCategoryEntity.java for fields description, musclesWorked and gifUrl
@Disabled
@DataJpaTest
class ExerciseCategoryRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ExerciseCategoryRepository exerciseCategoryRepository;

    @Test
    @WithMockUser(username = "admin", password = "topsecret", roles = {"ADMIN"})
    void testFindByCategory() {
        ExerciseCategoryEntity exerciseCategory =
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.CHEST)
                        .setDescription("Chest exercises")
                        .setGifUrl("https://media.giphy.com/media/3o7TKz9bX9v6ZvZ2nO/giphy.gif")
                        .setMusclesWorked("Chest, Triceps, Shoulders");

        testEntityManager.persist(exerciseCategory);
        testEntityManager.flush();

        Optional<ExerciseCategoryEntity> result = exerciseCategoryRepository.findByCategory(ExerciseCategoryEnum.CHEST);
        assertTrue(result.isPresent());
        assertEquals(ExerciseCategoryEnum.CHEST, result.get().getCategory());
        assertEquals("Chest exercises", result.get().getDescription());
        assertEquals("https://media.giphy.com/media/3o7TKz9bX9v6ZvZ2nO/giphy.gif", result.get().getGifUrl());
        assertEquals("Chest, Triceps, Shoulders", result.get().getMusclesWorked());
    }

    @Test
    @WithMockUser(username = "admin", password = "topsecret", roles = {"ADMIN"})
    void testFindByCategoryDBInit() {
        ExerciseCategoryEntity exerciseCategory =
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.CHEST)
                        .setDescription("Chest exercises")
                        .setGifUrl("https://media.giphy.com/media/3o7TKz9bX9v6ZvZ2nO/giphy.gif")
                        .setMusclesWorked("Chest, Triceps, Shoulders");

        testEntityManager.persist(exerciseCategory);
        testEntityManager.flush();

        Optional<ExerciseCategoryEntity> result = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.CHEST);
        assertTrue(result.isPresent());
        assertEquals(ExerciseCategoryEnum.CHEST, result.get().getCategory());
        assertEquals("Chest exercises", result.get().getDescription());
        assertEquals("https://media.giphy.com/media/3o7TKz9bX9v6ZvZ2nO/giphy.gif", result.get().getGifUrl());
        assertEquals("Chest, Triceps, Shoulders", result.get().getMusclesWorked());
    }
}