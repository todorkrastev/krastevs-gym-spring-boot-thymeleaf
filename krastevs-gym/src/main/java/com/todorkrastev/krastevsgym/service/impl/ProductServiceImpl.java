package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.PriceRangeNotFoundException;
import com.todorkrastev.krastevsgym.exception.ProductByCategoryIdAndDepartmentIdNotFoundException;
import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ProductDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ProductEntity;
import com.todorkrastev.krastevsgym.repository.ProductRepository;
import com.todorkrastev.krastevsgym.service.ExRateService;
import com.todorkrastev.krastevsgym.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.todorkrastev.krastevsgym.util.AppConstants.EUR;
import static com.todorkrastev.krastevsgym.util.AppConstants.USD;
import static com.todorkrastev.krastevsgym.util.AppConstants.CHF;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ExRateService exRateService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ExRateService exRateService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.exRateService = exRateService;
    }

    @Override
    public List<ProductShortInfoDTO> findAllByDepartmentId(Long categoryId) {
        List<ProductShortInfoDTO> products = productRepository.findAllByDepartmentCategory_IdOrderByCreatedAtDesc(categoryId)
                .stream()
                .map(productEntity -> {
                    ProductShortInfoDTO dto = modelMapper.map(productEntity, ProductShortInfoDTO.class);
                    List<String> currencies = exRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD);
                    dto.setCurrencies(currencies);

                    return dto;
                })
                .toList();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Department Category", "id", categoryId);
        }

        return products;
    }

    @Override
    public List<ProductShortInfoDTO> findByPriceRange(Long departmentId, String fromTo) {
        int[] values = extractFirstAndSecondValues(fromTo);
        int from = values[0];
        int to = values[1];

        List<ProductShortInfoDTO> products = productRepository
                .findAllByPriceBetweenAndDepartmentCategory_IdOrderByCreatedAtDesc(BigDecimal.valueOf(from), BigDecimal.valueOf(to), departmentId)
                .stream()
                .map(productEntity -> {
                    ProductShortInfoDTO dto = modelMapper.map(productEntity, ProductShortInfoDTO.class);
                    List<String> currencies = exRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD);
                    dto.setCurrencies(currencies);

                    return dto;
                })
                .toList();

        if (products.isEmpty()) {
            throw new PriceRangeNotFoundException(fromTo);
        }

        return products;
    }

    @Override
    public List<ProductShortInfoDTO> findAllByCategoryId(Long departmentId, Long categoryId) {
        List<ProductShortInfoDTO> products = productRepository
                .findAllByDepartmentCategory_IdAndCategory_IdOrderByCreatedAtDesc(departmentId, categoryId)
                .stream()
                .map(productEntity -> {
                    ProductShortInfoDTO dto = modelMapper.map(productEntity, ProductShortInfoDTO.class);
                    List<String> currencies = exRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD);
                    dto.setCurrencies(currencies);

                    return dto;
                })
                .toList();

        if (products.isEmpty()) {
            throw new ProductByCategoryIdAndDepartmentIdNotFoundException(departmentId, categoryId);
        }

        return products;
    }

    @Override
    public ProductDetailsDTO findById(Long id) {
        return productRepository.findById(id)
                .map(productEntity -> {
                    ProductDetailsDTO dto = modelMapper.map(productEntity, ProductDetailsDTO.class);
                    List<String> currencies = exRateService.getEURAndCHFAndUSDCurrencies(EUR, CHF, USD);
                    dto.setCurrencies(currencies);

                    return dto;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public Long deleteById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product", "id", id);
        }

        productRepository.deleteById(id);

        return product.get().getDepartmentCategory().getId();
    }

    private int[] extractFirstAndSecondValues(String input) {
        String regex = "from-(\\d+)-to-(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int from = Integer.parseInt(matcher.group(1));
            int to = Integer.parseInt(matcher.group(2));

            return new int[]{from, to};
        } else {
            throw new PriceRangeNotFoundException(input);
        }
    }
}
