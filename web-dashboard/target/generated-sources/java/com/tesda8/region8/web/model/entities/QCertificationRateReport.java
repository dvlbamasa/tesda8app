package com.tesda8.region8.web.model.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCertificationRateReport is a Querydsl query type for CertificationRateReport
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCertificationRateReport extends EntityPathBase<CertificationRateReport> {

    private static final long serialVersionUID = -408474734L;

    public static final QCertificationRateReport certificationRateReport = new QCertificationRateReport("certificationRateReport");

    public final QGeneralData _super = new QGeneralData(this);

    public final NumberPath<Long> assessed = createNumber("assessed", Long.class);

    public final NumberPath<Long> certified = createNumber("certified", Long.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.tesda8.region8.util.enums.OperatingUnitType> operatingUnitType = createEnum("operatingUnitType", com.tesda8.region8.util.enums.OperatingUnitType.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QCertificationRateReport(String variable) {
        super(CertificationRateReport.class, forVariable(variable));
    }

    public QCertificationRateReport(Path<? extends CertificationRateReport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCertificationRateReport(PathMetadata metadata) {
        super(CertificationRateReport.class, metadata);
    }

}

