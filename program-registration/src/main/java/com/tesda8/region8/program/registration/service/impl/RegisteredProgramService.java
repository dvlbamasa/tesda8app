package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.Sector;

import java.util.List;

public interface RegisteredProgramService {
    ProgramRegistrationWrapper getCourseCountPerInstitution();

    List<RegisteredProgramDto> getAllRegisteredProgramsByCourseSectorAndInstitutionClassification(Sector sector, InstitutionClassification institutionClassification);

    List<RegisteredProgramDto> getAllRegisteredProgramsByNameAndSectorAndCourseName(String[] institutionName,
                                                                                    Sector sector, String courseName);

    List<RegisteredProgramDto> getAllRegisteredProgramsWithFilter(RegisteredProgramFilter registeredProgramFilter);

    RegisteredProgram createRegisteredProgram(RegisteredProgramRequestDto registeredProgramDto);

    void saveRegisteredProgramRequirements(RegisteredProgramRequestDto registeredProgramRequestDto);

    void updateRegisteredProgram(RegisteredProgramRequestDto registeredProgramRequestDto);

    RegisteredProgramRequestDto getRegisteredProgramDto(Long id);

    void deleteRegisteredProgram(Long id);
}
