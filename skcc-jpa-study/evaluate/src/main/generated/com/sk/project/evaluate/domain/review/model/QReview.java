package com.sk.project.evaluate.domain.review.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 2125990944L;

    public static final QReview review = new QReview("review");

    public final com.sk.project.evaluate.domain.base.QAbstractEntity _super = new com.sk.project.evaluate.domain.base.QAbstractEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> registDate = createDateTime("registDate", java.util.Date.class);

    public final NumberPath<Long> restaurantId = createNumber("restaurantId", Long.class);

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

