package com.sk.project.evaluate.domain.score.model.entity;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Score extends AbstractEntity implements AggregateRoot {

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long storeId;

    @OneToOne(cascade= CascadeType.ALL)
    private EvaluationCategory evaluationCategory;

    private Integer starCount;

    private Date registDate;

    public Score(Long customerId, Long storeId, EvaluationCategory evaluationCategory, Integer starCount, Date registDate) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.evaluationCategory = evaluationCategory;
        this.starCount = starCount;
        this.registDate = registDate;
    }
}
