package com.sk.project.evaluate.domain.review.service;

import com.sk.project.evaluate.domain.review.model.dto.ReviewDto;
import com.sk.project.evaluate.domain.review.model.entity.Review;
import com.sk.project.evaluate.domain.review.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("reviewService")
public class RevieiwServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public RevieiwServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Review findReviewByCustomerIdAndStoreId(Long customerId, Long storeId) {
        return reviewRepository.findReviewByCustomerIdAndStoreId(customerId, storeId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Review> findReviewsByCustomerId(Long customerId) {
        return reviewRepository.findReviewsByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Review> findReviewsByStoreId(Long storeId) {
        return reviewRepository.findReviewsByStoreId(storeId);
    }

    @Transactional
    @Override
    public Review insertReview(ReviewDto reviewDto) {
        Review review = new Review(
                reviewDto.getCustomerId(),
                reviewDto.getStoreId(),
                reviewDto.getContent(),
                new Date(),
                new Date());
        return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public Review updateReview(Long reviewId, ReviewDto reviewDto) {
        Optional<Review> oldReview = reviewRepository.findById(reviewId);
        if (oldReview.isPresent()) {
            Review newReview = new Review(
                    reviewDto.getCustomerId(),
                    reviewDto.getStoreId(),
                    reviewDto.getContent(),
                    null,
                    new Date());
            BeanUtils.copyProperties(newReview, oldReview.get(), "id", "registDate");
            return reviewRepository.save(oldReview.get());
        } else {
            return null;
        }
    }
}
