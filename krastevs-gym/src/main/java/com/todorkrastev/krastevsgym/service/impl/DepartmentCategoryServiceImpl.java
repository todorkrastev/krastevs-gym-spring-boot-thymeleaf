package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.DepartmentCategoryDTO;
import com.todorkrastev.krastevsgym.repository.DepartmentCategoryRepository;
import com.todorkrastev.krastevsgym.service.DepartmentCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentCategoryServiceImpl implements DepartmentCategoryService {

    private final DepartmentCategoryRepository departmentCategoryRepository;
    private final ModelMapper modelMapper;

    public DepartmentCategoryServiceImpl(DepartmentCategoryRepository departmentCategoryRepository, ModelMapper modelMapper) {
        this.departmentCategoryRepository = departmentCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentCategoryDTO> findAll() {
        return departmentCategoryRepository
                .findAll()
                .stream()
                .map(departmentCategory -> modelMapper.map(departmentCategory, DepartmentCategoryDTO.class))
                .toList();
    }
}
