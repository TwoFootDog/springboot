package com.sk.project.evaluate.domain.review.model.entity;

import com.sk.project.evaluate.domain.base.AbstractEntity;
import com.sk.project.evaluate.domain.base.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "store_id"})})
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Review extends AbstractEntity implements AggregateRoot {
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    private String content;

    private Date registDate;

    private Date updateDate;

    public Review(Long customerId, Long storeId, String content, Date registDate, Date updateDate) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.content = content;
        this.registDate = registDate;
        this.updateDate = updateDate;
    }
}
