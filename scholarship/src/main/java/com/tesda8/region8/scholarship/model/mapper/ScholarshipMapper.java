package com.tesda8.region8.scholarship.model.mapper;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.scholarship.model.entities.ScholarshipAccomplishment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScholarshipMapper {
    ScholarshipMapper INSTANCE = Mappers.getMapper(ScholarshipMapper.class);

    @Mapping(source = "scholarshipAccomplishment.qualificationMap", target = "qualificationMapDto")
    @Mapping(source = "scholarshipAccomplishment.financialAccomplishment", target = "financialAccomplishmentDto")
    @Mapping(source = "scholarshipAccomplishment.physicalAccomplishment", target = "physicalAccomplishmentDto")
    @Mapping(source = "scholarshipAccomplishment.financialAccomplishment.poFinancialAccomplishment", target = "financialAccomplishmentDto.poFinancialAccomplishmentDto")
    @Mapping(source = "scholarshipAccomplishment.financialAccomplishment.roFinancialAccomplishment", target = "financialAccomplishmentDto.roFinancialAccomplishmentDto")
    ScholarshipAccomplishmentDto scholarshipAccomplishmentToDto(ScholarshipAccomplishment scholarshipAccomplishment);

    @Mapping(source = "scholarshipAccomplishmentDto.qualificationMapDto", target = "qualificationMap")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto", target = "financialAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.physicalAccomplishmentDto", target = "physicalAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto.poFinancialAccomplishmentDto", target = "financialAccomplishment.poFinancialAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto.roFinancialAccomplishmentDto", target = "financialAccomplishment.roFinancialAccomplishment")
    ScholarshipAccomplishment scholarshipAccomplishmentToEntity(ScholarshipAccomplishmentDto scholarshipAccomplishmentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "scholarshipAccomplishmentDto.qualificationMapDto", target = "qualificationMap")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto", target = "financialAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.physicalAccomplishmentDto", target = "physicalAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto.poFinancialAccomplishmentDto", target = "financialAccomplishment.poFinancialAccomplishment")
    @Mapping(source = "scholarshipAccomplishmentDto.financialAccomplishmentDto.roFinancialAccomplishmentDto", target = "financialAccomplishment.roFinancialAccomplishment")
    public abstract ScholarshipAccomplishment updatedScholarshipAccomplishmentToEntity(ScholarshipAccomplishmentDto scholarshipAccomplishmentDto,
                                                                                       @MappingTarget ScholarshipAccomplishment scholarshipAccomplishment);
}
