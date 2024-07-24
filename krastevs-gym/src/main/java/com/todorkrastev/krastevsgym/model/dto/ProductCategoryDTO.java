package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.ProductCategoryEnum;

public class ProductCategoryDTO {
    private Long id;
    private ProductCategoryEnum category;

    public ProductCategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public ProductCategoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductCategoryDTO setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }
}
