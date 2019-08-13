package com.sk.project.evaluate.application.controller;

import com.sk.project.evaluate.domain.score.model.dto.ScoreDto;
import com.sk.project.evaluate.domain.score.model.entity.Score;
import com.sk.project.evaluate.domain.score.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

//    @Autowired
//    public ScoreController(ScoreService scoreService) {
//        this.scoreService = scoreService;
//    }

    @GetMapping("/{customerId}/{scoreId}")
    public Score findScoreByCustomerIdAndScoreId(@PathVariable("customerId") Long customerId, @PathVariable("scoreId") Long scoreId) {
        return scoreService.findScoreByCustomerIdAndStoreId(customerId, scoreId);
    }

    @PostMapping
    public Score insertScore(@RequestBody ScoreDto scoreDto) {
        return scoreService.insertScore(scoreDto);
    }

    @PutMapping("/{scoreId}")
    public Score updateScore(@PathVariable Long scoreId, @RequestBody ScoreDto newScoreDto) {
        return scoreService.updateScore(scoreId, newScoreDto);
    }
}
