package com.sk.project.evaluate.domain.evaluation.model;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Score extends AbstractEntity implements AggregateRoot {
    private Long customerId;

    private Long restaurantId;

    @OneToMany(cascade= CascadeType.ALL)
    private EvaluationCategory evaluationCategory;

    private Integer starCount;

    private Date registDate;
}
