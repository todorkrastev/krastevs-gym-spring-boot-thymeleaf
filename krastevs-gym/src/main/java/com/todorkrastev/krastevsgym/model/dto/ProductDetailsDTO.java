package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.entity.DepartmentCategoryEntity;
import com.todorkrastev.krastevsgym.model.entity.PictureEntity;
import com.todorkrastev.krastevsgym.model.entity.PriceFilterEntity;
import com.todorkrastev.krastevsgym.model.entity.ProductCategoryEntity;

import java.math.BigDecimal;
import java.util.List;

public class ProductDetailsDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<ProductCategoryEntity> category;
    private List<DepartmentCategoryEntity> departmentCategory;
    private List<PriceFilterEntity> priceFilter;
    private List<PictureEntity> pictures;

    public ProductDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public ProductDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetailsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDetailsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDetailsDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<ProductCategoryEntity> getCategory() {
        return category;
    }

    public ProductDetailsDTO setCategory(List<ProductCategoryEntity> category) {
        this.category = category;
        return this;
    }

    public List<DepartmentCategoryEntity> getDepartmentCategory() {
        return departmentCategory;
    }

    public ProductDetailsDTO setDepartmentCategory(List<DepartmentCategoryEntity> departmentCategory) {
        this.departmentCategory = departmentCategory;
        return this;
    }

    public List<PriceFilterEntity> getPriceFilter() {
        return priceFilter;
    }

    public ProductDetailsDTO setPriceFilter(List<PriceFilterEntity> priceFilter) {
        this.priceFilter = priceFilter;
        return this;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ProductDetailsDTO setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
