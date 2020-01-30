package com.sk.sample.delivery.domain.review.model;

import javax.persistence.Entity;

import com.sk.sample.delivery.domain.base.AbstractEntity;
import com.sk.sample.delivery.domain.base.AggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Review extends AbstractEntity implements AggregateRoot {
    private Long accountId;
    private Long restaurantId;
    private String writer;
    private String content;
    private Integer starCount;


    public Review(Long accountId, Long restaurantId, String writer, String content, Integer starCount) {
        this.accountId = accountId;
        this.restaurantId = restaurantId;
        this.writer = writer;
        this.content = content;
        this.starCount = starCount;
    }
}
