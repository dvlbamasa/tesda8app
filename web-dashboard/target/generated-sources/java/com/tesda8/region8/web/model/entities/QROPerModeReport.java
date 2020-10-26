package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QROPerModeReport is a Querydsl query type for ROPerModeReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QROPerModeReport extends EntityPathBase<ROPerModeReport> {

    private static final long serialVersionUID = 1304081307L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QROPerModeReport rOPerModeReport = new QROPerModeReport("rOPerModeReport");

    public final QGeneralData _super = new QGeneralData(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<com.tesda8.region8.util.enums.DeliveryMode> deliveryMode = createEnum("deliveryMode", com.tesda8.region8.util.enums.DeliveryMode.class);

    public final QEgacData egacData;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.tesda8.region8.util.enums.ReportSourceType> reportSourceType = createEnum("reportSourceType", com.tesda8.region8.util.enums.ReportSourceType.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QROPerModeReport(String variable) {
        this(ROPerModeReport.class, forVariable(variable), INITS);
    }

    public QROPerModeReport(Path<? extends ROPerModeReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QROPerModeReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QROPerModeReport(PathMetadata metadata, PathInits inits) {
        this(ROPerModeReport.class, metadata, inits);
    }

    public QROPerModeReport(Class<? extends ROPerModeReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.egacData = inits.isInitialized("egacData") ? new QEgacData(forProperty("egacData")) : null;
    }

}

