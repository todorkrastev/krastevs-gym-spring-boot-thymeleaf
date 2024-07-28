package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.entity.PictureEntity;


import java.math.BigDecimal;
import java.util.List;

public class ProductDetailsDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<PictureEntity> pictures;
    private List<String> currencies;

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

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ProductDetailsDTO setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public ProductDetailsDTO setCurrencies(List<String> currencies) {
        this.currencies = currencies;
        return this;
    }
}
