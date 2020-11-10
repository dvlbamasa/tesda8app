package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionProgramRegCounter;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.Sector;

import java.util.List;

public interface InstitutionService {

    List<InstitutionDto> getAllInstitution();

    List<InstitutionDto> getAllInstitutionByInstitutionType(InstitutionType institutionType);

    List<InstitutionDto> getAllInstitutionWithFilter(InstitutionFilter institutionFilter);

    List<InstitutionDto> getAllInstitutionByInstitutionTypeAndInstitutionClassification(InstitutionType institutionType,
                                                                                        InstitutionClassification institutionClassification);

    List<InstitutionDto> getAllInstitutionByCourseSector(Sector sector);

    List<InstitutionDto> getAllInstitutionByCourseSectorAndInstitutionClassification(Sector sector, InstitutionClassification institutionClassification);

    List<InstitutionDto> getAllInstitutionByNameAndSectorAndCourseName(String[] institutionName,
                                                                                Sector sector, String courseName);

    List<InstitutionDto> getAllRegisteredProgramsWithFilter(RegisteredProgramFilter registeredProgramFilter);

    InstitutionProgramRegCounter getTotalCountOfRegisteredPrograms(List<InstitutionDto> institutionDtoList);

    ProgramRegistrationWrapper getCourseCountPerInstitution();
}
