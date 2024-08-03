package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.PriceRangeNotFoundException;
import com.todorkrastev.krastevsgym.exception.ProductByCategoryIdAndDepartmentIdNotFoundException;
import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ProductDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.DepartmentCategoryEntity;
import com.todorkrastev.krastevsgym.model.entity.ProductEntity;
import com.todorkrastev.krastevsgym.repository.ProductRepository;
import com.todorkrastev.krastevsgym.service.ExRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.todorkrastev.krastevsgym.util.AppConstants.EUR;
import static com.todorkrastev.krastevsgym.util.AppConstants.USD;
import static com.todorkrastev.krastevsgym.util.AppConstants.CHF;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository mockRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private ExRateService mockExRateService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(mockRepository, mockModelMapper, mockExRateService);
    }

    @Test
    void findAllByDepartmentId_DepartmentExists() {
        Long departmentId = 1L;
        ProductEntity productEntity = new ProductEntity();
        ProductShortInfoDTO productDTO = new ProductShortInfoDTO();
        List<String> currencies = List.of(EUR, CHF, USD);

        when(mockRepository.findAllByDepartmentCategory_IdOrderByCreatedAtDesc(departmentId))
                .thenReturn(List.of(productEntity));
        when(mockModelMapper.map(productEntity, ProductShortInfoDTO.class)).thenReturn(productDTO);
        when(mockExRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD)).thenReturn(currencies);

        List<ProductShortInfoDTO> result = productService.findAllByDepartmentId(departmentId);

        assertEquals(1, result.size());
        assertEquals(productDTO, result.getFirst());
        assertEquals(currencies, result.getFirst().getCurrencies());
    }

    @Test
    void findAllByDepartmentId_DepartmentNotFound() {
        Long departmentId = 1L;

        when(mockRepository.findAllByDepartmentCategory_IdOrderByCreatedAtDesc(departmentId))
                .thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class, () -> productService.findAllByDepartmentId(departmentId));
    }

    @Test
    void findByPriceRange_ProductsFound() {
        Long departmentId = 1L;
        String fromTo = "from-10-to-20";
        ProductEntity productEntity = new ProductEntity();
        ProductShortInfoDTO productDTO = new ProductShortInfoDTO();
        List<String> currencies = List.of(EUR, CHF, USD);

        when(mockRepository.findAllByPriceBetweenAndDepartmentCategory_IdOrderByCreatedAtDesc(
                BigDecimal.valueOf(10), BigDecimal.valueOf(20), departmentId))
                .thenReturn(List.of(productEntity));
        when(mockModelMapper.map(productEntity, ProductShortInfoDTO.class)).thenReturn(productDTO);
        when(mockExRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD)).thenReturn(currencies);

        List<ProductShortInfoDTO> result = productService.findByPriceRange(departmentId, fromTo);

        assertEquals(1, result.size());
        assertEquals(productDTO, result.getFirst());
        assertEquals(currencies, result.getFirst().getCurrencies());
    }

    @Test
    void findByPriceRange_ProductsNotFound() {
        Long departmentId = 1L;
        String fromTo = "from-10-to-20";

        when(mockRepository.findAllByPriceBetweenAndDepartmentCategory_IdOrderByCreatedAtDesc(
                BigDecimal.valueOf(10), BigDecimal.valueOf(20), departmentId))
                .thenReturn(List.of());

        assertThrows(PriceRangeNotFoundException.class, () -> productService.findByPriceRange(departmentId, fromTo));
    }

    @Test
    void findAllByCategoryId_ProductsFound() {
        Long departmentId = 1L;
        Long categoryId = 2L;
        ProductEntity productEntity = new ProductEntity();
        ProductShortInfoDTO productDTO = new ProductShortInfoDTO();
        List<String> currencies = List.of(EUR, CHF, USD);

        when(mockRepository.findAllByDepartmentCategory_IdAndCategory_IdOrderByCreatedAtDesc(departmentId, categoryId))
                .thenReturn(List.of(productEntity));
        when(mockModelMapper.map(productEntity, ProductShortInfoDTO.class)).thenReturn(productDTO);
        when(mockExRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD)).thenReturn(currencies);

        List<ProductShortInfoDTO> result = productService.findAllByCategoryId(departmentId, categoryId);

        assertEquals(1, result.size());
        assertEquals(productDTO, result.getFirst());
        assertEquals(currencies, result.getFirst().getCurrencies());
    }

    @Test
    void findAllByCategoryId_ProductsNotFound() {
        Long departmentId = 1L;
        Long categoryId = 2L;

        when(mockRepository.findAllByDepartmentCategory_IdAndCategory_IdOrderByCreatedAtDesc(departmentId, categoryId))
                .thenReturn(List.of());

        assertThrows(ProductByCategoryIdAndDepartmentIdNotFoundException.class, () -> productService.findAllByCategoryId(departmentId, categoryId));
    }

    @Test
    void findById_ProductFound() {
        Long productId = 1L;
        ProductEntity productEntity = new ProductEntity();
        ProductDetailsDTO productDTO = new ProductDetailsDTO();
        List<String> currencies = List.of(EUR, CHF, USD);

        when(mockRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(mockModelMapper.map(productEntity, ProductDetailsDTO.class)).thenReturn(productDTO);
        when(mockExRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD)).thenReturn(currencies);

        ProductDetailsDTO result = productService.findById(productId);

        assertEquals(productDTO, result);
        assertEquals(currencies, result.getCurrencies());
    }

    @Test
    void findById_ProductNotFound() {
        Long productId = 1L;

        when(mockRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.findById(productId));
    }

    @Test
    void deleteById_ProductFound() {
        Long productId = 1L;
        Long departmentCategoryId = 2L;
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDepartmentCategory(new DepartmentCategoryEntity().setId(departmentCategoryId));

        when(mockRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        Long result = productService.deleteById(productId);

        assertEquals(departmentCategoryId, result);
    }

    @Test
    void deleteById_ProductNotFound() {
        Long productId = 1L;

        when(mockRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteById(productId));
    }

    @Test
    void extractFirstAndSecondValues_InvalidInput_ThrowsPriceRangeNotFoundException() {
        String invalidInput = "invalid-input";

        assertThrows(PriceRangeNotFoundException.class, () -> productService.extractFirstAndSecondValues(invalidInput));
    }
}