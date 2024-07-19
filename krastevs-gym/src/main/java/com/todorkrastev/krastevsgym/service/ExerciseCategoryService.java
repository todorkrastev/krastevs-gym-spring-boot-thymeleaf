package com.todorkrastev.krastevsgym.service;


import com.todorkrastev.krastevsgym.model.dto.ExerciseCategoryInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseCategoryEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;

import java.util.List;

public interface ExerciseCategoryService {
    List<ExerciseCategoryInfoDTO> getAllCategories();

    void findById(Long id);

    ExerciseCategoryEntity findByCategory(ExerciseCategoryEnum category);
}
