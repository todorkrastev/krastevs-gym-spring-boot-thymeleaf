package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.repository.ExerciseCategoryRepository;
import com.todorkrastev.krastevsgym.service.ExerciseCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return exerciseCategoryRepository
                .findAll()
                .stream()
                .map(exerciseCategoryEntity ->
                        modelMapper.map(exerciseCategoryEntity, ExerciseCategoryInfoDTO.class))
                .toList();
    }

    @Override
    public void findById(Long id) {
        exerciseCategoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise category", "id", id));
    }

    @Override
    public ExerciseCategoryEntity findByCategory(ExerciseCategoryEnum category) {
        return exerciseCategoryRepository
                .findByCategory(category).orElse(null);
    }
}
