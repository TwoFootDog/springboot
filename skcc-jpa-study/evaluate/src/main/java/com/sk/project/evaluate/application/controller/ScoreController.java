package com.sk.project.evaluate.application.controller;

import com.sk.project.evaluate.domain.score.model.Score;
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
    public Score insertScore(@RequestBody Score score) {
        return scoreService.insertScore(score);
    }

    @PutMapping("/{scoreId}")
    public Score updateScore(@PathVariable Long scoreId, @RequestBody Score score) {
        return scoreService.updateScore(scoreId, score);
    }
}
