package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEgacData is a Querydsl query type for EgacData
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEgacData extends BeanPath<EgacData> {

    private static final long serialVersionUID = -1901494902L;

    public static final QEgacData egacData = new QEgacData("egacData");

    public final EnumPath<com.tesda8.region8.util.enums.EgacType> egacType = createEnum("egacType", com.tesda8.region8.util.enums.EgacType.class);

    public final NumberPath<Long> output = createNumber("output", Long.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Long> target = createNumber("target", Long.class);

    public QEgacData(String variable) {
        super(EgacData.class, forVariable(variable));
    }

    public QEgacData(Path<? extends EgacData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEgacData(PathMetadata metadata) {
        super(EgacData.class, metadata);
    }

}

