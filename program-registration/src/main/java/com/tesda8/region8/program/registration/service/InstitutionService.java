package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
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

    void createInstitution(InstitutionDto institutionDto);

    void updateInstitution(InstitutionDto institutionDto);

    InstitutionDto getInstitutionDto(Long id);

    void deleteInstitution(Long id);

}
