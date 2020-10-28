package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;

import java.util.List;

public interface InstitutionService {

    List<InstitutionDto> getAllInstitution();

    List<InstitutionDto> getAllInstitutionByCourseSector(Sector sector);

    List<InstitutionDto> getAllInstitutionByOperatingUnitAndSector(OperatingUnitType operatingUnitType, Sector sector);

    ProgramRegistrationWrapper getCourseCountPerInstitution();
}
