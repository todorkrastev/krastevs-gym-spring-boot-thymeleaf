package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.ProductCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    public ProductCategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public ProductCategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductCategoryEntity setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }
}
