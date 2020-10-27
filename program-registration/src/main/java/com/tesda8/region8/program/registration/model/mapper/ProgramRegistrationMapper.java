package com.tesda8.region8.program.registration.model.mapper;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgramRegistrationMapper {
    ProgramRegistrationMapper INSTANCE = Mappers.getMapper(ProgramRegistrationMapper.class);

    RegisteredProgram registeredProgramToEntity(RegisteredProgramDto programDto);
    RegisteredProgramDto registeredProgramToDto(RegisteredProgram registeredProgram);

    Institution institutionToEntity(InstitutionDto institutionDto);
    InstitutionDto institutionToDto(Institution institution);
}
