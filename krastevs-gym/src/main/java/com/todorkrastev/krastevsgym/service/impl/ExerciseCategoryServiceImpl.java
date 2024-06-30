package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.repository.ExerciseCategoryRepository;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseCategoryServiceImpl implements ExerciseCategoryService {

    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final ModelMapper modelMapper;


    public ExerciseCategoryServiceImpl(ExerciseCategoryRepository exerciseCategoryRepository, ModelMapper modelMapper) {
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExerciseCategoryInfoDTO> getAllCategories() {
        return exerciseCategoryRepository.findAll().stream().map(this::mapToInfo).toList();
    }

    @Override
    public ExerciseCategoryInfoDTO findById(Long id) {
        Optional<ExerciseCategoryEntity> byId = exerciseCategoryRepository.findById(id);

        //TODO: try catch

        ExerciseCategoryInfoDTO categoryInfoDTO = new ExerciseCategoryInfoDTO(
                byId.get().id,
                byId.get().exerciseCategory,
                byId.get().description,
                byId.get().gifUrl
        );

        return categoryInfoDTO;
    }

    private ExerciseCategoryInfoDTO mapToInfo(ExerciseCategoryEntity category) {
        return new ExerciseCategoryInfoDTO(
                category.getId(),
                category.getExerciseCategory(),
                category.getDescription(),
                category.getGifUrl());
    }
}
