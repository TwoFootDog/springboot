package com.sk.project.evaluate.domain.score.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {
    private Long customerId;

    private Long storeId;

    private Long evaluationCategoryId;

    private Integer starCount;

}
