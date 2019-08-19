package com.sk.project.evaluate.application.controller;

import com.sk.project.evaluate.domain.score.model.dto.ScoreDto;
import com.sk.project.evaluate.domain.score.model.entity.Score;
import com.sk.project.evaluate.domain.score.service.ScoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @ApiOperation(
            value = "평점 조회",
            httpMethod = "GET",
            notes = "특정 회원(customer)이 특정 가맹점(store)에 등록한 평점(score) 조회")
    @GetMapping("/customer/{customerId}/store/{storeId}/")
    public List<Score> findScoreByCustomerIdAndStoreId(@PathVariable("customerId") Long customerId, @PathVariable("storeId") Long storeId) {
        return scoreService.findScoreByCustomerIdAndStoreId(customerId, storeId);
    }

    @ApiOperation(
            value = "가맹점 평점 조회",
            httpMethod = "GET",
            notes = "특정 가맹점(store)에 등록된 평점(score) 조회")
    @GetMapping("/store/{storeId}")
    public List<Score> findScoresByStoreId(@PathVariable("storeId") Long storeId) {
        return scoreService.findScoresByStoreId(storeId);
    }

    @ApiOperation(
            value = "평점 등록",
            httpMethod = "POST",
            notes = "특정 회원(customer)이 특정 가맹점(store)에 대한 평점(score) 등록")
    @PostMapping
    public Score insertScore(@RequestBody ScoreDto scoreDto) {
        return scoreService.insertScore(scoreDto);
    }

    @ApiOperation(
            value = "평점 변경",
            httpMethod = "PUT",
            notes = "평점(score) 변경")
    @PutMapping("/{scoreId}")
    public Score updateScore(@PathVariable Long scoreId, @RequestBody ScoreDto newScoreDto) {
        return scoreService.updateScore(scoreId, newScoreDto);
    }
}
