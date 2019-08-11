package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.score.model.Score;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{
    @Override
    @Transactional(readOnly = true)
    public Score findScoreById(Long customerId, Long restaurantId) {
        return null;
    }

    @Override
    @Transactional
    public Score insertScore(Score score) {
        return null;
    }

    @Override
    @Transactional
    public Score updateScore(Score score) {
        return null;
    }
}
