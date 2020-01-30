package com.sk.project.evaluate.domain.review.repository;

import com.sk.project.evaluate.domain.review.model.entity.Review;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>, QuerydslPredicateExecutor<Review> {
    Review findReviewByCustomerIdAndStoreId(Long customerId, Long storeId);

    List<Review> findReviewsByCustomerId(Long customerId);

    List<Review> findReviewsByStoreId(Long storeId);
}
