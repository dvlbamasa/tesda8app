package com.tesda8.region8.planning.model.mapper;

import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.planning.model.dto.OperatingUnitDataDto;
import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlanningMapper {

    PlanningMapper INSTANCE = Mappers.getMapper(PlanningMapper.class);

    OperatingUnitData operatingUnitDataToEntity(OperatingUnitDataDto operatingUnitDataDto);
    OperatingUnitDataDto operatingUnitDataToDto(OperatingUnitData operatingUnitData);

    SuccessIndicatorData successIndicatorToEntity(SuccessIndicatorDataDto successIndicatorDataDto);
    @Mapping(source = "successIndicatorData.papData.name", target = "papName")
    @Mapping(source = "successIndicatorData.papData.papGroupType", target = "papGroupType")
    SuccessIndicatorDataDto successIndicatorToDto(SuccessIndicatorData successIndicatorData);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operatingUnitDataList", ignore = true)
    public abstract SuccessIndicatorData updatedSuccessIndicatorData(SuccessIndicatorDataDto successIndicatorDataDto,
                                                                     @MappingTarget SuccessIndicatorData successIndicatorData);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operatingUnitType", ignore = true)
    public abstract OperatingUnitData updatedOperatingUnitData(OperatingUnitDataDto operatingUnitDataDto,
                                                               @MappingTarget OperatingUnitData operatingUnitData);

    PapData papDataToEntity(PapDataDto papDataDto);
    PapDataDto papDataToDto(PapData papData);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "papData.id", target = "entityId")
    AuditLog papDataToAudit(PapData papData);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "successIndicatorData.id", target = "entityId")
    AuditLog successIndicatorDataToAudit(SuccessIndicatorData successIndicatorData);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "operatingUnitData.id", target = "entityId")
    AuditLog operatingUnitDataToAudit(OperatingUnitData operatingUnitData);
}
