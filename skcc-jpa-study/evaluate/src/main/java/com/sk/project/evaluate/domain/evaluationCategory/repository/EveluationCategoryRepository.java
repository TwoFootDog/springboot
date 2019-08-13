package com.sk.project.evaluate.domain.evaluationCategory.repository;

import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EveluationCategoryRepository extends PagingAndSortingRepository<EvaluationCategory, Long>,
                                                        QuerydslPredicateExecutor<EvaluationCategory> {
}
