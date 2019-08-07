package com.sk.project.evaluate.domain.evaluation.model;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Review extends AbstractEntity implements AggregateRoot {
    private Long customerId;

    private Long restaurantId;

    private String content;

    private Date registDate;
}
