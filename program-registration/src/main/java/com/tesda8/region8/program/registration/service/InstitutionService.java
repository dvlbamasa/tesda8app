package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.Sector;

import java.util.List;

public interface InstitutionService {

    List<InstitutionDto> getAllInstitution();

    List<InstitutionDto> getAllInstitutionWithFilter(InstitutionFilter institutionFilter);

    List<InstitutionDto> getAllInstitutionByInstitutionTypeAndInstitutionClassification(InstitutionType institutionType,
                                                                                        InstitutionClassification institutionClassification);

    List<InstitutionDto> getAllInstitutionByCourseSector(Sector sector);

    List<RegisteredProgramDto> getAllRegisteredProgramsByCourseSectorAndInstitutionClassification(Sector sector, InstitutionClassification institutionClassification);

    List<RegisteredProgramDto> getAllRegisteredProgramsByNameAndSectorAndCourseName(String[] institutionName,
                                                                                Sector sector, String courseName);

    List<RegisteredProgramDto> getAllRegisteredProgramsWithFilter(RegisteredProgramFilter registeredProgramFilter);

    ProgramRegistrationWrapper getCourseCountPerInstitution();

    void createRegisteredProgram(RegisteredProgramRequestDto registeredProgramDto);

    void createInstitution(InstitutionDto institutionDto);

    void updateRegisteredProgram(RegisteredProgramRequestDto registeredProgramRequestDto);

    void updateInstitution(InstitutionDto institutionDto);

    RegisteredProgramRequestDto getRegisteredProgramDto(Long id);

    InstitutionDto getInstitutionDto(Long id);

    void deleteRegisteredProgram(Long id);

    void deleteInstitution(Long id);
}
