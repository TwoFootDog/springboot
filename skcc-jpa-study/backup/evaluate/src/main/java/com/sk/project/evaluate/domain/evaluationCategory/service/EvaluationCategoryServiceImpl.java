package com.sk.project.evaluate.domain.evaluationCategory.service;

import com.sk.project.evaluate.domain.evaluationCategory.model.dto.EvaluationCategoryDto;
import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import com.sk.project.evaluate.domain.evaluationCategory.repository.EvaluationCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("evaluationCategoryService")
@Slf4j
public class EvaluationCategoryServiceImpl implements EvaluationCategoryService{

    private final EvaluationCategoryRepository evaluationCategoryRepository;

    @Autowired
    public EvaluationCategoryServiceImpl(EvaluationCategoryRepository evaluationCategoryRepository) {
        this.evaluationCategoryRepository = evaluationCategoryRepository;
    }

    @Override
    public List<EvaluationCategory> findAllEvaluationCategory() {
        List<EvaluationCategory> list = new ArrayList<>();
        Iterator<EvaluationCategory> iter = evaluationCategoryRepository.findAll().iterator();
        iter.forEachRemaining(list::add);
        return list;
    }

    @Override
    public EvaluationCategory insertEvaluationCategory(EvaluationCategoryDto evaluationCategoryDto) {
        EvaluationCategory evaluationCategory =
                new EvaluationCategory(
                        evaluationCategoryDto.getCategoryName(),
                        new Date(),
                        new Date());
        return evaluationCategoryRepository.save(evaluationCategory);
    }

    @Override
    public EvaluationCategory updateEvaluationCategory(Long evaluationCategoryId, EvaluationCategoryDto evaluationCategoryDto) {
        Optional<EvaluationCategory> oldEvaluationCategory = evaluationCategoryRepository.findById(evaluationCategoryId);
        if (oldEvaluationCategory.isPresent()) {
            EvaluationCategory newEvaluationCategory =
                    new EvaluationCategory(
                            evaluationCategoryDto.getCategoryName(),
                            null,
                            new Date());
            BeanUtils.copyProperties(newEvaluationCategory, oldEvaluationCategory.get(), "id", "registDate");
            return evaluationCategoryRepository.save(oldEvaluationCategory.get());
        } else {
            return null;
        }
    }
}