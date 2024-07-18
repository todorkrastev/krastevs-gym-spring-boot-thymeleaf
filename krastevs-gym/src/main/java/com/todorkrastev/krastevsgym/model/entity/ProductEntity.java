package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = PictureEntity.class, mappedBy = "product")
    private List<PictureEntity> pictures;

//    private ProductShortInfoDTO mapToInfo(ProductEntity product) {
//        ProductShortInfoDTO dto = modelMapper.map(product, ProductShortInfoDTO.class);
//
//        Optional<PictureEntity> first = product.getPictures().stream().findFirst();
//        first.ifPresent(pictureEntity -> dto.setImage(pictureEntity.getImage()));
//
//        return dto;
//    }

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

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ProductEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
