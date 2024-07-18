package com.todorkrastev.krastevsgym.service;


import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;

import java.util.List;

public interface ExerciseCategoryService {
    List<ExerciseCategoryInfoDTO> getAllCategories();

    ExerciseCategoryEntity findById(Long id);
}
