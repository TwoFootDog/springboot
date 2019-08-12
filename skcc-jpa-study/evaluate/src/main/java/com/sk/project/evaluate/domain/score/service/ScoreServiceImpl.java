package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.score.model.Score;
import com.sk.project.evaluate.domain.score.repository.ScoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreServiceImpl (ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Score findScoreByCustomerIdAndStoreId(Long customerId, Long storeId) {
        return scoreRepository.findScoreByCustomerIdAndStoreId(customerId, storeId);
    }

    @Override
    @Transactional
    public Score insertScore(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    @Transactional
    public Score updateScore(Long scoreId, Score newScore) {
        Optional<Score> oldScore = scoreRepository.findById(scoreId);
        if (oldScore.isPresent()) { // score가 존재하는 경우
            BeanUtils.copyProperties(newScore, oldScore, "id");
            return scoreRepository.save(newScore);
        } else {
            return null;
        }
    }
}
