package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.ProductCategoryDTO;
import com.todorkrastev.krastevsgym.model.entity.ProductCategoryEntity;
import com.todorkrastev.krastevsgym.repository.ProductCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceImplTest {

    private ProductCategoryServiceImpl productCategoryService;

    @Mock
    private ProductCategoryRepository mockRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setUp() {
        productCategoryService = new ProductCategoryServiceImpl(mockRepository, mockModelMapper);
    }

    @Test
    void findAll_ReturnsProductCategoryDTOList() {
        ProductCategoryEntity categoryEntity = new ProductCategoryEntity();
        ProductCategoryDTO categoryDTO = new ProductCategoryDTO();

        when(mockRepository.findAll()).thenReturn(List.of(categoryEntity));
        when(mockModelMapper.map(categoryEntity, ProductCategoryDTO.class)).thenReturn(categoryDTO);

        List<ProductCategoryDTO> result = productCategoryService.findAll();

        assertEquals(1, result.size());
        assertEquals(categoryDTO, result.getFirst());
    }
}