package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.score.model.dto.ScoreDto;
import com.sk.project.evaluate.domain.score.model.entity.Score;


public interface ScoreService {
    Score findScoreByCustomerIdAndStoreId(Long customerId, Long storeId);
    Score insertScore(ScoreDto scoreDto);
    Score updateScore(Long scoreId, ScoreDto newScoreDto);
}
