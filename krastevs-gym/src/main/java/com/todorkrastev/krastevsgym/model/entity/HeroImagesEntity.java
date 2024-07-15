package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.HeroImageCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "hero_images")
public class HeroImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    @Column(name = "hero_page_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private HeroImageCategoryEnum heroPageCategory;

    public HeroImagesEntity() {
    }

    public Long getId() {
        return id;
    }

    public HeroImagesEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public HeroImagesEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public HeroImagesEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public HeroImageCategoryEnum getHeroPageCategory() {
        return heroPageCategory;
    }

    public HeroImagesEntity setHeroPageCategory(HeroImageCategoryEnum heroPageCategory) {
        this.heroPageCategory = heroPageCategory;
        return this;
    }
}
