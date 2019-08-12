package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.score.model.Score;

public interface ScoreService {
    Score findScoreByCustomerIdAndStoreId(Long customerId, Long storeId);
    Score insertScore(Score score);
    Score updateScore(Long scoreId, Score newScore);
}
