package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMonthlyReport is a Querydsl query type for MonthlyReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonthlyReport extends EntityPathBase<MonthlyReport> {

    private static final long serialVersionUID = 726875557L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMonthlyReport monthlyReport = new QMonthlyReport("monthlyReport");

    public final com.tesda8.region8.util.model.QGeneralData _super = new com.tesda8.region8.util.model.QGeneralData(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QEgacData egacData;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.tesda8.region8.util.enums.Month> month = createEnum("month", com.tesda8.region8.util.enums.Month.class);

    public final QOperatingUnit operatingUnit;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QMonthlyReport(String variable) {
        this(MonthlyReport.class, forVariable(variable), INITS);
    }

    public QMonthlyReport(Path<? extends MonthlyReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMonthlyReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMonthlyReport(PathMetadata metadata, PathInits inits) {
        this(MonthlyReport.class, metadata, inits);
    }

    public QMonthlyReport(Class<? extends MonthlyReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.egacData = inits.isInitialized("egacData") ? new QEgacData(forProperty("egacData")) : null;
        this.operatingUnit = inits.isInitialized("operatingUnit") ? new QOperatingUnit(forProperty("operatingUnit")) : null;
    }

}

