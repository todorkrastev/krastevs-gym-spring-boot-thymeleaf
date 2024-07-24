package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;

import java.util.List;

public interface ProductService {
    List<ProductShortInfoDTO> findAllByDepartmentId(Long categoryId);
}
