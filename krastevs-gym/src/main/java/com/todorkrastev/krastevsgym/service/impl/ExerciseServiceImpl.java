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
    private static final String SAMPLE_EXERCISE_IMAGE = "https://res.cloudinary.com/dgtuddxqf/image/upload/v1721504899/krastevs-gym/imgs/exercise/exercise-sample-plan-c_hr3xgb.jpg";
private static final String SAMPLE_MUSCLES_WORKED_IMAGE = "https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg";

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
            gifUrl = SAMPLE_EXERCISE_IMAGE;
        }

        ExerciseEntity exercise = modelMapper.map(createExerciseDTO, ExerciseEntity.class);
        exercise
                .setGifUrl(gifUrl)
                .setMusclesWorkedUrl(SAMPLE_MUSCLES_WORKED_IMAGE)
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
    public ExerciseDetailsDTO editExercise(Long id, EditExerciseDTO editExerciseDTO) {
        ExerciseEntity exercise = exerciseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));

        ExerciseCategoryEntity category = exerciseCategoryService.findByCategory(editExerciseDTO.getCategory());
        if (category == null) {
            throw new CategoryNotFoundException("Category", "id", editExerciseDTO.getCategory().toString());
        }

        String gifUrl = editExerciseDTO.getGifUrl();
        if (gifUrl.isBlank()) {
            gifUrl = SAMPLE_EXERCISE_IMAGE;
            modelMapper.map(editExerciseDTO, ExerciseEntity.class);
            editExerciseDTO.setGifUrl(gifUrl);
        } else {
            modelMapper.map(editExerciseDTO, ExerciseEntity.class);
        }

        exerciseRepository.save(exercise);

        return modelMapper.map(exercise, ExerciseDetailsDTO.class);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseDetailsDTO getExerciseDetails(Long id) {
        return this.exerciseRepository
                .findById(id)
                .map(exercise -> {
                    ExerciseDetailsDTO exerciseDetailsDTO = modelMapper.map(exercise, ExerciseDetailsDTO.class);
                    exerciseDetailsDTO.setCreatorId(exercise.getUser().getId());

                    return exerciseDetailsDTO;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));
    }

    @Override
    public List<ExerciseShortInfoDTO> getExercisesByCategoryIdAndUserId(Long categoryId, Long userId) {
        //validation if the id exists in the db
        exerciseCategoryService.findById(categoryId);

        Long adminId = userService.findAdminId();
        List<ExerciseEntity> allExercisesByCategoryId = exerciseRepository.findAllByCategoryIdAndAdminIdAndUserId(categoryId, adminId, userId);

        return allExercisesByCategoryId
                .stream()
                .map(exercise -> modelMapper.map(exercise, ExerciseShortInfoDTO.class))
                .toList();
    }
}
