package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.entity.PictureEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductShortInfoDTO {
    private Long id;
    private String name;
    private List<PictureEntity> pictures;
    private BigDecimal price;
    private DepartmentCategoryDTO departmentCategory;
    private List<String> currencies;

    public ProductShortInfoDTO() {
        this.currencies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ProductShortInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductShortInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ProductShortInfoDTO setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductShortInfoDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public DepartmentCategoryDTO getDepartmentCategory() {
        return departmentCategory;
    }

    public ProductShortInfoDTO setDepartmentCategory(DepartmentCategoryDTO departmentCategory) {
        this.departmentCategory = departmentCategory;
        return this;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public ProductShortInfoDTO setCurrencies(List<String> currencies) {
        this.currencies = currencies;
        return this;
    }
}
