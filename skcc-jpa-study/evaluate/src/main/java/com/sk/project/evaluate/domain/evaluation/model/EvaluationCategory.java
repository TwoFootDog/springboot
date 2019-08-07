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
public class EvaluationCategory extends AbstractEntity implements AggregateRoot {
    private String itemName;

    private Date registDate;
}
