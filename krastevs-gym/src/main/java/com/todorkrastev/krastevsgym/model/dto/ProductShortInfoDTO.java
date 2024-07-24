package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.entity.PictureEntity;

import java.math.BigDecimal;
import java.util.List;

public class ProductShortInfoDTO {
    private Long id;
    private String name;
    private String description;
    private List<PictureEntity> pictures;
    private BigDecimal price;

    public ProductShortInfoDTO() {
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

    public String getDescription() {
        return description;
    }

    public ProductShortInfoDTO setDescription(String description) {
        this.description = description;
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
}
