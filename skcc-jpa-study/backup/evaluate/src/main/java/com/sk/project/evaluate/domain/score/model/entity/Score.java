package com.sk.project.evaluate.domain.score.model.entity;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "store_id", "evaluation_category_id"})})
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Score extends AbstractEntity implements AggregateRoot {
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @OneToOne(cascade = CascadeType.MERGE)
    private EvaluationCategory evaluationCategory;

    private Integer starCount;

    private Date registDate;

    private Date updateDate;

    public Score(Long customerId,
                 Long storeId,
                 EvaluationCategory evaluationCategory,
                 Integer starCount,
                 Date registDate,
                 Date updateDate) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.evaluationCategory = evaluationCategory;
        this.starCount = starCount;
        this.registDate = registDate;
        this.updateDate = updateDate;
    }
}
