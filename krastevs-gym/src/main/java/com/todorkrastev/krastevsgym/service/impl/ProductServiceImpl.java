package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;
import com.todorkrastev.krastevsgym.repository.ProductRepository;
import com.todorkrastev.krastevsgym.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductShortInfoDTO> findAllByDepartmentId(Long categoryId) {
        return productRepository.findAllByDepartmentCategory_Id(categoryId)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductShortInfoDTO.class))
                .toList();
    }
}
