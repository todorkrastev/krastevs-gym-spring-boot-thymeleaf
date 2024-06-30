package com.todorkrastev.krastevsgym.service;


import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;

import java.util.List;

public interface ExerciseCategoryService {
    List<ExerciseCategoryInfoDTO> getAllCategories();

    ExerciseCategoryInfoDTO findById(Long id);
}
