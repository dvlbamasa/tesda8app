package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.repository.InstitutionRepository;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private InstitutionRepository institutionRepository;
    private ProgramRegistrationMapper programRegistrationMapper;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository,
                                  ProgramRegistrationMapper programRegistrationMapper) {
        this.institutionRepository = institutionRepository;
        this.programRegistrationMapper = programRegistrationMapper;
    }

    @Override
    public List<InstitutionDto> getAllInstitution() {
        List<Institution> institutions;
        institutions = institutionRepository.findAll();
        return institutions
                .stream()
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
    }

    @Override
    public List<InstitutionDto> getAllInstitutionByCourseSector(Sector sector) {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = institutions
                .stream()
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
        institutionDtos.forEach(
                institutionDto -> {
                    institutionDto.setRegisteredPrograms(
                            institutionDto.getRegisteredPrograms()
                                    .stream()
                                    .filter(programDto -> programDto.getSector().equals(sector))
                                    .collect(Collectors.toList())
                    );
                }
        );
        return institutionDtos;
    }
}
