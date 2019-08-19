package com.sk.project.evaluate.domain.evaluationCategory.repository;

import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EvaluationCategoryRepository extends PagingAndSortingRepository<EvaluationCategory, Long>,
                                                        QuerydslPredicateExecutor<EvaluationCategory> {
}
