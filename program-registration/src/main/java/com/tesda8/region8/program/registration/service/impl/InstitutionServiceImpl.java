package com.tesda8.region8.program.registration.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.entities.QInstitution;
import com.tesda8.region8.program.registration.model.entities.QRegisteredProgram;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.model.wrapper.CourseCount;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionWrapper;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.repository.InstitutionRepository;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private static Logger logger = LoggerFactory.getLogger(InstitutionServiceImpl.class);

    private InstitutionRepository institutionRepository;
    private RegisteredProgramRepository registeredProgramRepository;
    private ProgramRegistrationMapper programRegistrationMapper;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository,
                                  ProgramRegistrationMapper programRegistrationMapper,
                                  RegisteredProgramRepository registeredProgramRepository) {
        this.institutionRepository = institutionRepository;
        this.programRegistrationMapper = programRegistrationMapper;
        this.registeredProgramRepository = registeredProgramRepository;
    }

    @Override
    public List<InstitutionDto> getAllInstitution() {
        List<Institution> institutions = institutionRepository.findAll();
        institutions.forEach(
                institution -> {
                    institution.setRegisteredPrograms(
                            institution.getRegisteredPrograms()
                            .stream()
                            .filter(registeredProgram -> !registeredProgram.getIsDeleted())
                            .collect(Collectors.toList())
                    );
                }
        );
        return institutions
                .stream()
                .filter(institution -> !institution.getIsDeleted())
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
    }

    @Override
    public List<InstitutionDto> getAllInstitutionWithFilter(InstitutionFilter institutionFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanBuilder operatingUnitTypePredicate = new BooleanBuilder();
        Arrays.asList(institutionFilter.getOperatingUnitType())
                .forEach(operatingUnitType -> {
                    if (!operatingUnitType.equals(OperatingUnitType.TOTAL)) {
                        operatingUnitTypePredicate.or(QInstitution.institution.operatingUnitType.eq(operatingUnitType));
                    }
                });
        booleanBuilder.and(operatingUnitTypePredicate);
        BooleanBuilder institutionTypePredicate = new BooleanBuilder();
        Arrays.asList(institutionFilter.getInstitutionType())
                .forEach(institutionType -> {
                    if (!institutionType.equals(InstitutionType.ALL)) {
                        institutionTypePredicate.or(QInstitution.institution.institutionType.eq(institutionType));
                    }
                });
        booleanBuilder.and(institutionTypePredicate);
        BooleanBuilder institutionClassificationPredicate = new BooleanBuilder();
        Arrays.asList(institutionFilter.getInstitutionClassification())
                .forEach(institutionClassification -> {
                    if (!institutionClassification.equals(InstitutionClassification.ALL)) {
                        institutionClassificationPredicate.or(QInstitution.institution.institutionClassification.eq(institutionClassification));
                    }
                });
        booleanBuilder.and(institutionClassificationPredicate);
        BooleanBuilder institutionsPredicate = new BooleanBuilder();
        Arrays.asList(institutionFilter.getInstitutionIds())
                .forEach(id -> {
                    if (id != 0) {
                        institutionsPredicate.or(QInstitution.institution.id.eq(id));
                    }
                });

        booleanBuilder.and(institutionsPredicate);
        booleanBuilder.and(QInstitution.institution.isDeleted.eq(false));
        booleanBuilder.and(QInstitution.institution.address.toLowerCase().trim()
                .contains(institutionFilter.getAddress().trim().toLowerCase()));
        booleanBuilder.and(QInstitution.institution.contactNumber.toLowerCase().trim()
                .contains(institutionFilter.getContactNumber().trim().toLowerCase()));

        Predicate predicate = booleanBuilder.getValue();

        List<Institution> institutions = (List<Institution>) institutionRepository.findAll(predicate);

        return institutions
                .stream()
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
    }

    @Override
    public List<InstitutionDto> getAllInstitutionByInstitutionTypeAndInstitutionClassification(InstitutionType institutionType, InstitutionClassification institutionClassification) {
        List<Institution> institutions = institutionRepository
                .findAllByInstitutionTypeAndInstitutionClassification(InstitutionType.PUBLIC, InstitutionClassification.TESDA);
        return institutions
                .stream()
                .filter(institution -> !institution.getIsDeleted())
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
    }

    @Override
    public List<InstitutionDto> getAllInstitutionByCourseSector(Sector sector) {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = institutions
                .stream()
                .filter(institution -> !institution.getIsDeleted())
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());
        institutionDtos.forEach(
                institutionDto -> {
                    institutionDto.setRegisteredPrograms(
                            institutionDto.getRegisteredPrograms()
                                    .stream()
                                    .filter(registeredProgramDto -> !registeredProgramDto.getIsDeleted())
                                    .filter(registeredProgramDto -> !registeredProgramDto.getIsClosed())
                                    .filter(registeredProgramDto -> !registeredProgramDto.getCourseStatus().equals(CourseStatus.BUNDLED_PROGRAM))
                                    .filter(programDto -> programDto.getSector().equals(sector))
                                    .collect(Collectors.toList())
                    );
                }
        );
        return institutionDtos;
    }

    @Override
    @Transactional
    public void createInstitution(InstitutionDto institutionDto) {
        Institution institution = programRegistrationMapper.institutionToEntity(institutionDto);
        institution.setRegisteredPrograms(Lists.newArrayList());
        institution.setIsDeleted(false);
        institutionRepository.save(institution);
    }

    @Override
    @Transactional
    public void updateInstitution(InstitutionDto institutionDto) {
        Institution institution = institutionRepository.findById(institutionDto.getId()).orElseThrow(EntityNotFoundException::new);
        institution = programRegistrationMapper.updatedInstitutionToEntity(institutionDto, institution);
        institution.setUpdatedDate(LocalDateTime.now());
        institutionRepository.save(institution);
    }

    @Override
    public InstitutionDto getInstitutionDto(Long id) {
        Institution institution = institutionRepository.getOne(id);
        return programRegistrationMapper.institutionToDto(institution);
    }

    @Override
    @Transactional
    public void deleteInstitution(Long id) {
        Institution institution = institutionRepository.getOne(id);
        institution.setIsDeleted(true);
        institution.setUpdatedDate(LocalDateTime.now());
        institutionRepository.save(institution);
    }
}
