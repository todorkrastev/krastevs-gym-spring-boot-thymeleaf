package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.DepartmentCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "department_categories")
public class DepartmentCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageURL;

    @Enumerated(EnumType.STRING)
    private DepartmentCategoryEnum category;

    public DepartmentCategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public DepartmentCategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public DepartmentCategoryEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public DepartmentCategoryEnum getCategory() {
        return category;
    }

    public DepartmentCategoryEntity setCategory(DepartmentCategoryEnum category) {
        this.category = category;
        return this;
    }
}
