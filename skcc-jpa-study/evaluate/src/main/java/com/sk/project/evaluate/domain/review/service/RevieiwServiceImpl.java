package com.sk.project.evaluate.domain.review.service;

import com.sk.project.evaluate.domain.review.model.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reviewService")
public class RevieiwServiceImpl implements ReviewService{
    @Transactional(readOnly = true)
    @Override
    public Review findReviewById(Long customerId, Long storeId) {
        return null;
    }

    @Transactional
    @Override
    public Review insertReview(Review review) {
        return null;
    }

    @Transactional
    @Override
    public Review updateReview(Long reviewId, Review review) {
        return null;
    }
}
