package com.sk.project.evaluate.domain.review.repository;

import com.sk.project.evaluate.domain.review.model.Review;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>, QuerydslPredicateExecutor<Review> {
}
