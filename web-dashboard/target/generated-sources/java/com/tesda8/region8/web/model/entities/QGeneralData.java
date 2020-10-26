package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeneralData is a Querydsl query type for GeneralData
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QGeneralData extends EntityPathBase<GeneralData> {

    private static final long serialVersionUID = -685976650L;

    public static final QGeneralData generalData = new QGeneralData("generalData");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QGeneralData(String variable) {
        super(GeneralData.class, forVariable(variable));
    }

    public QGeneralData(Path<? extends GeneralData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeneralData(PathMetadata metadata) {
        super(GeneralData.class, metadata);
    }

}

