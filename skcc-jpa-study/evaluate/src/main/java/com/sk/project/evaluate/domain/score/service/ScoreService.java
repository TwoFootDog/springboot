package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.score.model.Score;

public interface ScoreService {
    Score findScoreById(Long customerId, Long restaurantId);
    Score insertScore(Score score);
    Score updateScore(Score score);
}
