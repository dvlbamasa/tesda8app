package com.tesda8.region8.program.registration.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.entities.QInstitution;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.model.wrapper.CourseCount;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionProgramRegCounter;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionWrapper;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.repository.InstitutionRepository;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private static Logger logger = LoggerFactory.getLogger(InstitutionServiceImpl.class);


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
    public List<InstitutionDto> getAllInstitutionByInstitutionType(InstitutionType institutionType) {
        List<Institution> institutions = institutionRepository.findAllByInstitutionType(institutionType);
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

    @Override
    public List<InstitutionDto> getAllInstitutionByNameAndSectorAndCourseName(String institutionName,
                                                                              Sector sector,
                                                                              String courseName) {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = institutions
                .stream()
                .filter(institution -> institutionName.equals("") || institution.getName().equalsIgnoreCase(institutionName))
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
        institutionDtos.forEach(
                institutionDto -> {
                    institutionDto.setRegisteredPrograms(
                            institutionDto.getRegisteredPrograms()
                                    .stream()
                                    .filter(programDto -> programDto.getSector().equals(sector))
                                    .filter(programDto -> programDto.getName().toLowerCase().contains(courseName.toLowerCase()))
                                    .collect(Collectors.toList())
                    );
                }
        );
        return institutionDtos;
    }

    public InstitutionProgramRegCounter getTotalCountOfRegisteredPrograms(List<InstitutionDto> institutionDtoList) {
        InstitutionProgramRegCounter institutionProgramRegCounter = new InstitutionProgramRegCounter();
        institutionDtoList.forEach(
                institutionDto -> {
                     institutionProgramRegCounter.setTotalRegisteredPrograms(institutionDto.getRegisteredPrograms().size() +
                             institutionProgramRegCounter.getTotalRegisteredPrograms());
                }
        );
        institutionProgramRegCounter.setTotalInstitutions(institutionDtoList.size());
        return institutionProgramRegCounter;
    }

    @Override
    public ProgramRegistrationWrapper getCourseCountPerInstitution() {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = institutions
                .stream()
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
        List<InstitutionWrapper> institutionWrappers = Lists.newArrayList();

        // for total count
        List<CourseCount> totalCourseCounts = Lists.newArrayList();
        InstitutionWrapper total = new InstitutionWrapper();
        total.setOperatingUnitType(OperatingUnitType.TOTAL);
        total.setInstitutionShortName("Total");
        total.setCourseCountList(totalCourseCounts);
        Arrays.asList(Sector.values()).forEach(sector -> {
            CourseCount courseCount = new CourseCount();
            courseCount.setSector(sector);
            total.getCourseCountList().add(courseCount);
        });
        //

        institutionDtos.forEach(
                institutionDto -> {
                    InstitutionWrapper institutionWrapper = new InstitutionWrapper();
                    institutionWrapper.setOperatingUnitType(institutionDto.getOperatingUnitType());
                    institutionWrapper.setInstitutionName(institutionDto.getName());
                    institutionWrapper.setInstitutionShortName(institutionDto.getShortName());

                    Arrays.asList(Sector.values()).forEach(sector -> {
                        CourseCount courseCount = new CourseCount();
                        courseCount.setSector(sector);
                        institutionDto.getRegisteredPrograms()
                                .forEach(programDto -> {
                                    if (sector.equals(programDto.getSector())) {
                                        courseCount.setCount(courseCount.getCount()+1);
                                        // for total
                                        total.getCourseCountList().forEach(
                                                courseCount1 -> {
                                                    if (courseCount1.getSector().equals(sector)) {
                                                        courseCount1.setCount(courseCount1.getCount()+1);
                                                    }
                                                }
                                        );
                                        //
                                    }
                                });
                        institutionWrapper.getCourseCountList().add(courseCount);
                    });
                    institutionWrappers.add(institutionWrapper);
                }
        );
        institutionWrappers.add(total);


        ProgramRegistrationWrapper programRegistrationWrapper = new ProgramRegistrationWrapper();

        institutionWrappers.forEach( institutionWrapper -> {
            switch (institutionWrapper.getOperatingUnitType()) {
                case LEYTE:
                    programRegistrationWrapper.getLeyteInstitutionWrapperList().add(institutionWrapper);
                    break;
                case SAMAR:
                    programRegistrationWrapper.getSamarInstitutionWrapperList().add(institutionWrapper);
                    break;
                case BILIRAN:
                    programRegistrationWrapper.getBiliranInstitutionWrapperList().add(institutionWrapper);
                    break;
                case EASTERN_SAMAR:
                    programRegistrationWrapper.getEasternSamarInstitutionWrapperList().add(institutionWrapper);
                    break;
                case NORTHERN_SAMAR:
                    programRegistrationWrapper.getNorthernSamarInstitutionWrapperList().add(institutionWrapper);
                    break;
                case SOUTHERN_LEYTE:
                    programRegistrationWrapper.getSouthernLeyteInstitutionWrapperList().add(institutionWrapper);
                    break;
                case TOTAL:
                    programRegistrationWrapper.getTotalInstitutionWrapperList().add(institutionWrapper);
                    break;
                default:
                    break;
            }
        });


        return programRegistrationWrapper;
    }
}
