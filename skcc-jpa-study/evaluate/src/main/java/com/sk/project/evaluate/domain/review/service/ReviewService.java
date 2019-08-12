package com.sk.project.evaluate.domain.review.service;

import com.sk.project.evaluate.domain.review.model.Review;

public interface ReviewService {
    Review findReviewById(Long customerId, Long storeId);
    Review insertReview(Review review);
    Review updateReview(Long reviewId, Review review);
}
