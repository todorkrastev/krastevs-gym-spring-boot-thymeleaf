package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.DepartmentCategoryEnum;

public class DepartmentCategoryDTO {
    private Long id;
    private String imageURL;
    private DepartmentCategoryEnum category;

    public DepartmentCategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public DepartmentCategoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public DepartmentCategoryDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public DepartmentCategoryEnum getCategory() {
        return category;
    }

    public DepartmentCategoryDTO setCategory(DepartmentCategoryEnum category) {
        this.category = category;
        return this;
    }
}
