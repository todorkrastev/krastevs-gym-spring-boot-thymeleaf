package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.ProductCategoryDTO;
import com.todorkrastev.krastevsgym.repository.ProductCategoryRepository;
import com.todorkrastev.krastevsgym.service.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper modelMapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository, ModelMapper modelMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        return productCategoryRepository
                .findAll()
                .stream()
                .map(productCategory -> modelMapper.map(productCategory, ProductCategoryDTO.class))
                .toList();
    }
}
