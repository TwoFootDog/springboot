package com.sk.project.evaluate.application.controller;

import com.sk.project.evaluate.domain.review.model.dto.ReviewDto;
import com.sk.project.evaluate.domain.review.model.entity.Review;
import com.sk.project.evaluate.domain.review.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation(
            value = "회원 별 특정 가맹점 리뷰 조회",
            httpMethod = "GET",
            notes = "특정 회원(customer)이 특정 가맹점(store)에 등록한 리뷰(review) 조회")
    @GetMapping("/{customerId}/{storeId}")
    public Review findReviewByCustomerIdAndStoreId(@PathVariable("customerId") Long customerId,
                                                   @PathVariable("storeId") Long storeId) {
        return reviewService.findReviewByCustomerIdAndStoreId(customerId, storeId);
    }

    @ApiOperation(value = "회원 별 전체 리뷰 조회", httpMethod = "GET",
            notes = "특정 회원(customer)이 등록한 전체 리뷰(review) 조회")
    @GetMapping("/{customerId}")
    public List<Review> findReviewsByCustomerId(@PathVariable("customerId") Long customerId) {
        return reviewService.findReviewsByCustomerId(customerId);
    }

    @ApiOperation(
            value = "가맹점 별 전체 리뷰 조회",
            httpMethod = "GET",
            notes = "가맹점(store)에 등록된 전체 리뷰(review) 조회")
    @GetMapping("/{storeId}")
    public List<Review> findReviewsByStoreId(@PathVariable("storeId") Long storeId) {
        return reviewService.findReviewsByStoreId(storeId);
    }

    @ApiOperation(
            value = "리뷰 등록",
            httpMethod = "POST",
            notes = "특정 회원(customer)이 특정 가맹점(store)에 대한 리뷰(review) 등록")
    @PostMapping
    public Review insertReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.insertReview(reviewDto);
    }

    @ApiOperation(
            value = "리뷰 변경",
            httpMethod = "PUT",
            notes = "리뷰를 변경한다")
    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(reviewId, reviewDto);
    }
}
