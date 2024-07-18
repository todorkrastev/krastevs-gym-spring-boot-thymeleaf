package com.todorkrastev.krastevsgym.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "ex_rates")
public class ExRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String currency;

    @Positive
    @NotNull
    private BigDecimal rate;

    public ExRateEntity() {
    }

    public Long getId() {
        return id;
    }

    public ExRateEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public @NotEmpty String getCurrency() {
        return currency;
    }

    public ExRateEntity setCurrency(@NotEmpty String currency) {
        this.currency = currency;
        return this;
    }

    public @Positive @NotNull BigDecimal getRate() {
        return rate;
    }

    public ExRateEntity setRate(@Positive @NotNull BigDecimal rate) {
        this.rate = rate;
        return this;
    }
}
