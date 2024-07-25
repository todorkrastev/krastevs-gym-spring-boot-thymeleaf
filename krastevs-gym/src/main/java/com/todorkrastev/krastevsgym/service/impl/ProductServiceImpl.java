package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.PriceRangeNotFoundException;
import com.todorkrastev.krastevsgym.exception.ProductByCategoryIdAndDepartmentIdNotFoundException;
import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ProductShortInfoDTO;
import com.todorkrastev.krastevsgym.repository.ProductRepository;
import com.todorkrastev.krastevsgym.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        List<ProductShortInfoDTO> products = productRepository.findAllByDepartmentCategory_Id(categoryId)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductShortInfoDTO.class))
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
                .findAllByPriceBetweenAndDepartmentCategory_Id(BigDecimal.valueOf(from), BigDecimal.valueOf(to), departmentId)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductShortInfoDTO.class))
                .toList();

        if (products.isEmpty()) {
            throw new PriceRangeNotFoundException(fromTo);
        }

        return products;
    }

    @Override
    public List<ProductShortInfoDTO> findAllByCategoryId(Long departmentId, Long categoryId) {
        List<ProductShortInfoDTO> products = productRepository
                .findAllByDepartmentCategory_IdAndCategory_Id(departmentId, categoryId)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductShortInfoDTO.class))
                .toList();

        if (products.isEmpty()) {
            throw new ProductByCategoryIdAndDepartmentIdNotFoundException(departmentId, categoryId);
        }

        return products;
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
