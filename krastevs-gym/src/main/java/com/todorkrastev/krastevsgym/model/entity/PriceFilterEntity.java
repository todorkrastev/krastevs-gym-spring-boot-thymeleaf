package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.PriceFilterEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "price_filters")
public class PriceFilterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PriceFilterEnum filter;

    public PriceFilterEntity() {
    }

    public Long getId() {
        return id;
    }

    public PriceFilterEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PriceFilterEnum getFilter() {
        return filter;
    }

    public PriceFilterEntity setFilter(PriceFilterEnum filter) {
        this.filter = filter;
        return this;
    }
}
