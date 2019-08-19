package com.sk.project.evaluate.domain.score.service;

import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import com.sk.project.evaluate.domain.score.model.dto.ScoreDto;
import com.sk.project.evaluate.domain.score.model.entity.Score;
import com.sk.project.evaluate.domain.score.repository.ScoreRepository;
import com.sk.project.evaluate.utils.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    public List<Score> findScoreByCustomerIdAndStoreId(Long customerId, Long storeId) {
        return scoreRepository.findScoreByCustomerIdAndStoreId(customerId, storeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Score> findScoresByStoreId(Long storeId) {
        return scoreRepository.findScoresByStoreId(storeId);
    }

    @Override
    @Transactional
    public Score insertScore(ScoreDto scoreDto) {
        Score score = new Score(
                scoreDto.getCustomerId(),
                scoreDto.getStoreId(),
                new EvaluationCategory(scoreDto.getEvaluationCategoryId(), new Date(), new Date()),
                scoreDto.getStarCount(),
                new Date(),
                new Date());
        Score score1;
        try {
            score1 = scoreRepository.save(score);
        } catch (RuntimeException e) {
            throw new ServiceException("데이터를 등록할 수 없습니다");
        }
        return score1;
    }

    @Override
    @Transactional
    public Score updateScore(Long scoreId, ScoreDto scoreDto) {
        Optional<Score> oldScore = scoreRepository.findById(scoreId);
        if (oldScore.isPresent()) { // score가 존재하는 경우
                    Score newScore = new Score(
                            null,
                            null,
                            null,
                            scoreDto.getStarCount(),
                            null,
                            new Date());
            BeanUtils.copyProperties(newScore, oldScore.get(),
                    "id", "customerId", "storeId", "evaluationCategory", "registDate");
            try {
                return scoreRepository.save(oldScore.get());
            } catch (Exception e) {
                throw new ServiceException("데이터를 등록할 수 없습니다");
            }
        } else {
            return null;
        }
    }
}
