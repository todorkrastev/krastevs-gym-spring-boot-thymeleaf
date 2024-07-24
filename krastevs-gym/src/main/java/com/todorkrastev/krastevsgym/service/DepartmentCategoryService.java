package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.DepartmentCategoryDTO;

import java.util.List;

public interface DepartmentCategoryService {
    List<DepartmentCategoryDTO> findAll();
}
