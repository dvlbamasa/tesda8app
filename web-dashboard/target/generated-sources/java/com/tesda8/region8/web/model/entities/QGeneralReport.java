package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGeneralReport is a Querydsl query type for GeneralReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGeneralReport extends EntityPathBase<GeneralReport> {

    private static final long serialVersionUID = -1689164224L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGeneralReport generalReport = new QGeneralReport("generalReport");

    public final QGeneralData _super = new QGeneralData(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<com.tesda8.region8.util.enums.DailyReportType> dailyReportType = createEnum("dailyReportType", com.tesda8.region8.util.enums.DailyReportType.class);

    public final QEgacData egacData;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.tesda8.region8.util.enums.OperatingUnitType> operatingUnitType = createEnum("operatingUnitType", com.tesda8.region8.util.enums.OperatingUnitType.class);

    public final EnumPath<com.tesda8.region8.util.enums.ReportSourceType> reportSourceType = createEnum("reportSourceType", com.tesda8.region8.util.enums.ReportSourceType.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QGeneralReport(String variable) {
        this(GeneralReport.class, forVariable(variable), INITS);
    }

    public QGeneralReport(Path<? extends GeneralReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGeneralReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGeneralReport(PathMetadata metadata, PathInits inits) {
        this(GeneralReport.class, metadata, inits);
    }

    public QGeneralReport(Class<? extends GeneralReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.egacData = inits.isInitialized("egacData") ? new QEgacData(forProperty("egacData")) : null;
    }

}

