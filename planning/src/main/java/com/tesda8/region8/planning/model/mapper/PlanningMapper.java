package com.tesda8.region8.planning.model.mapper;

import com.tesda8.region8.planning.model.dto.OperatingUnitDataDto;
import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlanningMapper {

    PlanningMapper INSTANCE = Mappers.getMapper(PlanningMapper.class);

    OperatingUnitData operatingUnitDataToEntity(OperatingUnitDataDto operatingUnitDataDto);
    OperatingUnitDataDto operatingUnitDataToDto(OperatingUnitData operatingUnitData);

    SuccessIndicatorData successIndicatorToEntity(SuccessIndicatorDataDto successIndicatorDataDto);
    SuccessIndicatorDataDto successIndicatorToDto(SuccessIndicatorData successIndicatorData);

    PapData papDataToEntity(PapDataDto papDataDto);
    PapDataDto papDataToDto(PapData papData);
}
