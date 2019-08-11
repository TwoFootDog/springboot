package com.sk.project.evaluate.domain.score.model;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import com.sk.project.evaluate.domain.evaluationCategory.model.EvaluationCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Score extends AbstractEntity implements AggregateRoot {
    private Long customerId;

    private Long restaurantId;

    @OneToOne(cascade= CascadeType.ALL)
    private EvaluationCategory evaluationCategory;

    private Integer starCount;

    private Date registDate;
}
