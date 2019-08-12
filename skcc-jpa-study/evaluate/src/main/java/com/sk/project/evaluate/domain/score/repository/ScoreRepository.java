package com.sk.project.evaluate.domain.score.repository;

import com.sk.project.evaluate.domain.score.model.Score;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ScoreRepository extends PagingAndSortingRepository<Score, Long>, QuerydslPredicateExecutor<Score> {
    Score findScoreByCustomerIdAndStoreId(Long customerId, Long storeId);
}
