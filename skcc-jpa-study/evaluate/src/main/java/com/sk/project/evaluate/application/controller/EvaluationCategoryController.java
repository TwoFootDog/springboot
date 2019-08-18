package com.sk.project.evaluate.application.controller;

import com.sk.project.evaluate.domain.evaluationCategory.model.dto.EvaluationCategoryDto;
import com.sk.project.evaluate.domain.evaluationCategory.model.entity.EvaluationCategory;
import com.sk.project.evaluate.domain.evaluationCategory.service.EvaluationCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class EvaluationCategoryController {

    private final EvaluationCategoryService evaluationCategoryService;

    @Autowired
    public EvaluationCategoryController(EvaluationCategoryService evaluationCategoryService) {
        this.evaluationCategoryService = evaluationCategoryService;
    }

    @ApiOperation(
            value = "전체 평가항목 조회",
            httpMethod = "GET",
            notes = "전체 평가항목(Evaluation Category) 조회")
    @GetMapping
    public List<EvaluationCategory> findAllEvaluationCategory() {
        return evaluationCategoryService.findAllEvaluationCategory();
    }

    @ApiOperation(
            value = "평가항목 등록",
            httpMethod = "POST",
            notes = "평가항목(Evaluation Category) 등록")
    @PostMapping
    public EvaluationCategory insertEvaluationCategory(@RequestBody EvaluationCategoryDto evaluationCategoryDto) {
        return evaluationCategoryService.insertEvaluationCategory(evaluationCategoryDto);
    }

    @ApiOperation(
            value = "평가항목 변경",
            httpMethod = "PUT",
            notes = "평가항목(Evaluation Category) 변경")
    @PutMapping("/{evaluationCategoryId}")
    public EvaluationCategory updateEvaluationCategory(@PathVariable("evaluationCategoryId") Long evaluationCategoryId,
                                                       @RequestBody EvaluationCategoryDto evaluationCategoryDto) {
        return evaluationCategoryService.updateEvaluationCategory(evaluationCategoryId, evaluationCategoryDto);
    }
}
