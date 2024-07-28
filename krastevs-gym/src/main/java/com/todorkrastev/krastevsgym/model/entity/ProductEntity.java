package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @ManyToOne(targetEntity = ProductCategoryEntity.class, optional = false)
    private ProductCategoryEntity category;

    @ManyToOne(targetEntity = DepartmentCategoryEntity.class, optional = false)
    private DepartmentCategoryEntity departmentCategory;

    @ManyToOne(targetEntity = PriceFilterEntity.class, optional = false)
    private PriceFilterEntity priceFilter;

    @OneToMany(targetEntity = PictureEntity.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PictureEntity> pictures;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

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

    public DepartmentCategoryEntity getDepartmentCategory() {
        return departmentCategory;
    }

    public ProductEntity setDepartmentCategory(DepartmentCategoryEntity departmentCategory) {
        this.departmentCategory = departmentCategory;
        return this;
    }

    public PriceFilterEntity getPriceFilter() {
        return priceFilter;
    }

    public ProductEntity setPriceFilter(PriceFilterEntity priceFilter) {
        this.priceFilter = priceFilter;
        return this;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ProductEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ProductEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
