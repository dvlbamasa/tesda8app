package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOperatingUnit is a Querydsl query type for OperatingUnit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOperatingUnit extends EntityPathBase<OperatingUnit> {

    private static final long serialVersionUID = -32766871L;

    public static final QOperatingUnit operatingUnit = new QOperatingUnit("operatingUnit");

    public final QGeneralData _super = new QGeneralData(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<MonthlyReport, QMonthlyReport> monthlyReports = this.<MonthlyReport, QMonthlyReport>createList("monthlyReports", MonthlyReport.class, QMonthlyReport.class, PathInits.DIRECT2);

    public final EnumPath<com.tesda8.region8.util.enums.OperatingUnitType> operatingUnitType = createEnum("operatingUnitType", com.tesda8.region8.util.enums.OperatingUnitType.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QOperatingUnit(String variable) {
        super(OperatingUnit.class, forVariable(variable));
    }

    public QOperatingUnit(Path<? extends OperatingUnit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperatingUnit(PathMetadata metadata) {
        super(OperatingUnit.class, metadata);
    }

}

