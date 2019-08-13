package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import com.sk.project.evaluate.domain.score.model.dto.ScoreDto;
import com.sk.project.evaluate.domain.score.model.entity.Score;
import com.sk.project.evaluate.domain.score.repository.ScoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Score insertScore(ScoreDto scoreDto) {
        Score score = new Score(
                scoreDto.getCustomerId(),
                scoreDto.getStoreId(),
                new EvaluationCategory(scoreDto.getEvaluationCategoryDto().getItemName()),
                scoreDto.getStarCount(),
                new Date());
        return scoreRepository.save(score);
    }

    @Override
    @Transactional
    public Score updateScore(Long scoreId, ScoreDto newScoreDto) {
        Optional<Score> oldScore = scoreRepository.findById(scoreId);
        if (oldScore.isPresent()) { // score가 존재하는 경우
                    Score newScore = new Score(
                    newScoreDto.getCustomerId(),
                    newScoreDto.getStoreId(),
                    new EvaluationCategory(newScoreDto.getEvaluationCategoryDto().getItemName()),
                    newScoreDto.getStarCount(),
                    oldScore.get().getRegistDate());
            BeanUtils.copyProperties(newScore, oldScore, "id");
            return scoreRepository.save(newScore);
        } else {
            return null;
        }
    }
}
