package com.sk.project.evaluate.domain.evaluationCategory.model.entity;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class EvaluationCategory extends AbstractEntity implements AggregateRoot {
    @Column(unique = true, nullable = false)
    private String categoryName;

    private Date registDate;

    private Date updateDate;

    public EvaluationCategory(Long id, String categoryName, Date registDate, Date updateDate) {
        this.id = id;
        this.categoryName = categoryName;
        this.registDate = registDate;
        this.updateDate = updateDate;
    }

    public EvaluationCategory(Long id, Date registDate, Date updateDate) {
        this.id = id;
        this.registDate = registDate;
        this.updateDate = updateDate;
    }

    public EvaluationCategory(Long id) {
        this.id = id;
    }

    public EvaluationCategory(String categoryName, Date registDate, Date updateDate) {
        this.categoryName = categoryName;
        this.registDate = registDate;
        this.updateDate = updateDate;
    }

    public EvaluationCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
