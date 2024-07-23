package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    private ProductCategoryEntity category;

    //    @OneToMany(targetEntity = PictureEntity.class, mappedBy = "product")
    @OneToMany(targetEntity = PictureEntity.class, cascade = CascadeType.PERSIST)
    private List<PictureEntity> pictures;

    public ProductEntity() {
        this.pictures = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ProductEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductCategoryEntity getCategory() {
        return category;
    }

    public ProductEntity setCategory(ProductCategoryEntity category) {
        this.category = category;
        return this;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ProductEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
