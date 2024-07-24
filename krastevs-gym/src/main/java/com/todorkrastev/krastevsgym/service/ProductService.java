package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;

import java.util.List;

public interface ProductService {
    List<ProductShortInfoDTO> findAllByDepartmentId(Long categoryId);

    List<ProductShortInfoDTO> findByPriceRange(Long departmentId, String fromTO);

    List<ProductShortInfoDTO> findAllByCategoryId(Long departmentId, Long categoryId);
}
