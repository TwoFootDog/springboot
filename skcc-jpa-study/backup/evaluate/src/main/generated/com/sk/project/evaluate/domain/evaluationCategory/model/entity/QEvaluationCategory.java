package com.sk.project.evaluate.domain.evaluationCategory.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvaluationCategory is a Querydsl query type for EvaluationCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEvaluationCategory extends EntityPathBase<EvaluationCategory> {

    private static final long serialVersionUID = 2101046079L;

    public static final QEvaluationCategory evaluationCategory = new QEvaluationCategory("evaluationCategory");

    public final com.sk.project.evaluate.domain.base.QAbstractEntity _super = new com.sk.project.evaluate.domain.base.QAbstractEntity(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> registDate = createDateTime("registDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public QEvaluationCategory(String variable) {
        super(EvaluationCategory.class, forVariable(variable));
    }

    public QEvaluationCategory(Path<? extends EvaluationCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvaluationCategory(PathMetadata metadata) {
        super(EvaluationCategory.class, metadata);
    }

}

