package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDTO> findAll();
}
