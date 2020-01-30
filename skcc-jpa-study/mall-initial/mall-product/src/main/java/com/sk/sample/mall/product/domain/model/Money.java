package com.sk.sample.mall.product.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Money {
    private int value;

    public Money(int value) {
        this.value = value;
    }
}
