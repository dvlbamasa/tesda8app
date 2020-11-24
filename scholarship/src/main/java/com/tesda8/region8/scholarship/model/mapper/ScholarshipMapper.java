package com.tesda8.region8.scholarship.model.mapper;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.scholarship.model.entities.ScholarshipAccomplishment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScholarshipMapper {
    ScholarshipMapper INSTANCE = Mappers.getMapper(ScholarshipMapper.class);

    @Mapping(source = "scholarshipAccomplishment.qualificationMap", target = "qualificationMapDto")
    @Mapping(source = "scholarshipAccomplishment.financialAccomplishment", target = "financialAccomplishmentDto")
    @Mapping(source = "scholarshipAccomplishment.physicalAccomplishment", target = "physicalAccomplishmentDto")
    ScholarshipAccomplishmentDto scholarshipAccomplishmentToDto(ScholarshipAccomplishment scholarshipAccomplishment);

    @Mapping(source = "scholarshipAccomplishmentDto.qualificationMapDto", target = "qualificationMap")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto", target = "financialAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.physicalAccomplishmentDto", target = "physicalAccomplishment")
    ScholarshipAccomplishment scholarshipAccomplishmentToEntity(ScholarshipAccomplishmentDto scholarshipAccomplishmentDto);
}
