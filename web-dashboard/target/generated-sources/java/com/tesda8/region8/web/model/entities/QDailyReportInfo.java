package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDailyReportInfo is a Querydsl query type for DailyReportInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDailyReportInfo extends EntityPathBase<DailyReportInfo> {

    private static final long serialVersionUID = -102294529L;

    public static final QDailyReportInfo dailyReportInfo = new QDailyReportInfo("dailyReportInfo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.time.LocalDateTime> updatedDate = createDateTime("updatedDate", java.time.LocalDateTime.class);

    public QDailyReportInfo(String variable) {
        super(DailyReportInfo.class, forVariable(variable));
    }

    public QDailyReportInfo(Path<? extends DailyReportInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyReportInfo(PathMetadata metadata) {
        super(DailyReportInfo.class, metadata);
    }

}

