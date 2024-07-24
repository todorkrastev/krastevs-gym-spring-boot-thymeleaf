package com.todorkrastev.krastevsgym.model.dto;

import com.todorkrastev.krastevsgym.model.enums.PriceFilterEnum;


public class PriceFilterDTO {
    private Long id;

    private PriceFilterEnum filter;

    public PriceFilterDTO() {
    }

    public Long getId() {
        return id;
    }

    public PriceFilterDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public PriceFilterEnum getFilter() {
        return filter;
    }

    public PriceFilterDTO setFilter(PriceFilterEnum filter) {
        this.filter = filter;
        return this;
    }
}
