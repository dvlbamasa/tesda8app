package com.tesda8.region8.web.model.entities.planning;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPOData is a Querydsl query type for POData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPOData extends EntityPathBase<POData> {

    private static final long serialVersionUID = 757941022L;

    public static final QPOData pOData = new QPOData("pOData");

    public final com.tesda8.region8.web.model.entities.QGeneralData _super = new com.tesda8.region8.web.model.entities.QGeneralData(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.tesda8.region8.util.enums.OperatingUnitType> operatingUnitType = createEnum("operatingUnitType", com.tesda8.region8.util.enums.OperatingUnitType.class);

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QPOData(String variable) {
        super(POData.class, forVariable(variable));
    }

    public QPOData(Path<? extends POData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPOData(PathMetadata metadata) {
        super(POData.class, metadata);
    }

}

