package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.CategoryNotFoundException;
import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.*;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgym.model.entity.UserEntity;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import com.todorkrastev.krastevsgym.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private static String LOGO = "https://res.cloudinary.com/dgtuddxqf/image/upload/v1721424066/krastevs-gym/imgs/logo/logo_xpwwcv.png";
    private static Long ADMIN_ID = 1L;


    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    private final ExerciseCategoryService exerciseCategoryService;
    private final UserService userService;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper, ExerciseCategoryService exerciseCategoryService, UserService userService) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
        this.exerciseCategoryService = exerciseCategoryService;
        this.userService = userService;
    }

    @Override
    public Long createExercise(CreateExerciseDTO createExerciseDTO) {
        UserEntity currUser = userService.findUserById(createExerciseDTO.getCurrUserId());
        if (currUser == null) {
            throw new ResourceNotFoundException("User", "id", createExerciseDTO.getCurrUserId());
        }

        ExerciseCategoryEntity category = exerciseCategoryService.findByCategory(createExerciseDTO.getCategory());
        if (category == null) {
            throw new CategoryNotFoundException("Category", "id", createExerciseDTO.getCategory().toString());
        }

        String gifUrl = createExerciseDTO.getGifUrl();
        if (gifUrl.isBlank()) {
            gifUrl = LOGO;
        }

        ExerciseEntity exercise = modelMapper.map(createExerciseDTO, ExerciseEntity.class);
        exercise
                .setGifUrl(gifUrl)
                .setMusclesWorkedUrl(category.getMusclesWorkedUrl())
                .setCategory(category)
                .setUser(currUser);

        return exerciseRepository.save(exercise).getId();
    }

    @Override
    public void createExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long id) {
        exerciseRepository
                .findById(id)
                .map(exercise -> exercise.setNotes(createExerciseNotesDTO.getNotes())).ifPresent(exerciseRepository::save);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseDetailsDTO getExerciseDetails(Long id) {
        return this.exerciseRepository
                .findById(id)
                .map(ExerciseServiceImpl::toExerciseDetails)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));
    }

    @Override
    public List<ExerciseShortInfoDTO> getExercisesByCategoryIdAndUserId(Long categoryId, Long userId) {
        //validation if the id exists in the db
        exerciseCategoryService.findById(categoryId);

        List<ExerciseEntity> allExercisesByCategoryId = exerciseRepository.findAllByCategoryIdAndAdminIdAndUserId(categoryId, ADMIN_ID, userId);

        System.out.println();

        return allExercisesByCategoryId
                .stream()
                .map(exercise -> modelMapper.map(exercise, ExerciseShortInfoDTO.class))
                .toList();
    }

    private static ExerciseDetailsDTO toExerciseDetails(ExerciseEntity exerciseEntity) {
        return new ExerciseDetailsDTO()
                .setId(exerciseEntity.getId())
                .setName(exerciseEntity.getName())
                .setDescription(exerciseEntity.getDescription())
                .setGifUrl(exerciseEntity.getGifUrl())
                .setMusclesWorkedUrl(exerciseEntity.getMusclesWorkedUrl())
                .setInstructions(exerciseEntity.getInstructions())
                .setNotes(exerciseEntity.getNotes());
    }
}
