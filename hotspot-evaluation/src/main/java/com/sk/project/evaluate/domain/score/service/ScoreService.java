package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.score.model.dto.ScoreDto;
import com.sk.project.evaluate.domain.score.model.entity.Score;

import java.util.List;


public interface ScoreService {
    List<Score> findScoreByCustomerIdAndStoreId(Long customerId, Long storeId);

    List<Score> findScoresByStoreId(Long storeId);

    Score insertScore(ScoreDto scoreDto);

    Score updateScore(Long scoreId, ScoreDto scoreDto);
}
