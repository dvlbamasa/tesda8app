package com.tesda8.region8.program.registration.model.mapper;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgramRegistrationMapper {
    ProgramRegistrationMapper INSTANCE = Mappers.getMapper(ProgramRegistrationMapper.class);

    RegisteredProgram registeredProgramToEntity(RegisteredProgramDto programDto);
    RegisteredProgram registeredProgramToEntity(RegisteredProgramRequestDto registeredProgramRequestDto);
    RegisteredProgramDto registeredProgramToDto(RegisteredProgram registeredProgram);
    RegisteredProgramRequestDto registeredProgramToRequestDto(RegisteredProgram registeredProgram);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateIssued", ignore = true)
    public abstract RegisteredProgram updatedRegisteredProgramToEntity(RegisteredProgramRequestDto registeredProgramDto, @MappingTarget RegisteredProgram registeredProgram);

    Institution institutionToEntity(InstitutionDto institutionDto);
    InstitutionDto institutionToDto(Institution institution);

    @Mapping(target = "registeredPrograms", ignore = true)
    public abstract Institution updatedInstitutionToEntity(InstitutionDto institutionDto, @MappingTarget Institution institution);
}
