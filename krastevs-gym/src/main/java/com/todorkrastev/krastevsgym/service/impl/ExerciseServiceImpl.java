package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgym.model.entity.PictureEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final Logger LOGGER = LoggerFactory.getLogger(ExerciseServiceImpl.class);
    private final RestClient exercisesRestClient;
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    private final ExerciseCategoryService exerciseCategoryService;

    public ExerciseServiceImpl(@Qualifier("exercisesRestClient") RestClient exercisesRestClient, ExerciseRepository exerciseRepository, ModelMapper modelMapper, ExerciseCategoryService exerciseCategoryService) {
        this.exercisesRestClient = exercisesRestClient;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
        this.exerciseCategoryService = exerciseCategoryService;
    }

    @Override
    public List<ExerciseDetailsDTO> getAllExercises() {
        LOGGER.info("Get all exercises");

        //REST
        return exercisesRestClient
                .get()
                .uri("http://localhost:8081/exercises")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public void createExercise(CreateExerciseDTO createExerciseDTO) {
        // Thymeleaf

//        ExerciseEntity exercise = new ExerciseEntity()
//                .setName(createExerciseDTO.name())
//                .setDescription(createExerciseDTO.description())
//                .setEquipmentTypeEnum(createExerciseDTO.equipmentTypeEnum())
//                .setExerciseCategory(createExerciseDTO.exerciseCategoryEnum())
//                .setInstructions(createExerciseDTO.instructions())
//                .setGifUrl(createExerciseDTO.videoUrl());
//
//
//        //  ExerciseEntity exercise = modelMapper.map(createExerciseDTO, ExerciseEntity.class);
//        return exerciseRepository.save(exercise).getId();

        // REST

        LOGGER.info("Creating new exercise");

        exercisesRestClient
                .post()
                .uri("http://localhost:8081/exercises")
                .body(createExerciseDTO)
                .retrieve();
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseDetailsDTO getExerciseDetails(Long id) {
        //Thymeleaf
//        return this.exerciseRepository
//                .findById(id)
//                .map(ExerciseServiceImpl::toExerciseDetails)
//                .orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));

        return exercisesRestClient
                .get()
                .uri("http://localhost:8081/exercises/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ExerciseDetailsDTO.class);
    }

    @Override
    public List<ExerciseShortInfoDTO> getExercisesByCategoryId(Long id) {
        //validation if the id exists in the db
        exerciseCategoryService.findById(id);

        List<ExerciseEntity> allExercisesByCategoryId = exerciseRepository.findAllByCategoryId(id);

        return allExercisesByCategoryId
                .stream()
                .map(exercise -> modelMapper.map(exercise, ExerciseShortInfoDTO.class))
                .toList();
    }

    private static ExerciseDetailsDTO toExerciseDetails(ExerciseEntity exerciseEntity) {
        return new ExerciseDetailsDTO(exerciseEntity.getId(),
                exerciseEntity.getName(),
                exerciseEntity.getDescription(),
                exerciseEntity.getGifUrl(),
                exerciseEntity.getMusclesWorkedUrl(),
                exerciseEntity.getInstructions(),
                exerciseEntity.getNotes());
    }
}
