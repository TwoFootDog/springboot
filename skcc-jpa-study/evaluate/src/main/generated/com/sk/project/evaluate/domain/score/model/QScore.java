package com.sk.project.evaluate.domain.score.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScore is a Querydsl query type for Score
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScore extends EntityPathBase<Score> {

    private static final long serialVersionUID = -1108156226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScore score = new QScore("score");

    public final com.sk.project.evaluate.domain.base.QAbstractEntity _super = new com.sk.project.evaluate.domain.base.QAbstractEntity(this);

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final com.sk.project.evaluate.domain.evaluationCategory.model.QEvaluationCategory evaluationCategory;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> registDate = createDateTime("registDate", java.util.Date.class);

    public final NumberPath<Integer> starCount = createNumber("starCount", Integer.class);

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    public QScore(String variable) {
        this(Score.class, forVariable(variable), INITS);
    }

    public QScore(Path<? extends Score> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScore(PathMetadata metadata, PathInits inits) {
        this(Score.class, metadata, inits);
    }

    public QScore(Class<? extends Score> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.evaluationCategory = inits.isInitialized("evaluationCategory") ? new com.sk.project.evaluate.domain.evaluationCategory.model.QEvaluationCategory(forProperty("evaluationCategory")) : null;
    }

}

