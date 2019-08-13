package com.sk.project.evaluate.domain.score.model.dto;

import com.sk.project.evaluate.domain.evaluationCategory.model.dto.EvaluationCategoryDto;
import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {
    private Long customerId;

    private Long storeId;

    private EvaluationCategoryDto evaluationCategoryDto;

    private Integer starCount;

}
