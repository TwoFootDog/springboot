package com.sk.project.evaluate.domain.review.service;

import com.sk.project.evaluate.domain.review.model.dto.ReviewDto;
import com.sk.project.evaluate.domain.review.model.entity.Review;

import java.util.List;

public interface ReviewService {
    Review findReviewByCustomerIdAndStoreId(Long customerId, Long storeId);

    List<Review> findReviewsByCustomerId(Long customerId);

    List<Review> findReviewsByStoreId(Long storeId);

    Review insertReview(ReviewDto reviewDto);

    Review updateReview(Long reviewId, ReviewDto reviewDto);
}
