package com.sk.project.evaluate.domain.evaluationCategory.service;

import com.sk.project.evaluate.domain.evaluationCategory.model.dto.EvaluationCategoryDto;
import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;

import java.util.List;

public interface EvaluationCategoryService {
    List<EvaluationCategory> findAllEvaluationCategory();

    EvaluationCategory insertEvaluationCategory(EvaluationCategoryDto evaluationCategoryDto);

    EvaluationCategory updateEvaluationCategory(Long evaluationCategoryId, EvaluationCategoryDto evaluationCategoryDto);
}
