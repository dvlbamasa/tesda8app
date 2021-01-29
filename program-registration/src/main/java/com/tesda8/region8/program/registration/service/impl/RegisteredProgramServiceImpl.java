package com.tesda8.region8.program.registration.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.entities.QRegisteredProgram;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.model.wrapper.CourseCount;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionWrapper;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.repository.InstitutionRepository;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.RegisteredProgramService;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.SortOrder;
import com.tesda8.region8.util.service.ApplicationUtil;
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
public class RegisteredProgramServiceImpl implements RegisteredProgramService {

    private static final String ALL = "ALL";
    private static Logger logger = LoggerFactory.getLogger(RegisteredProgramServiceImpl.class);


    private InstitutionRepository institutionRepository;
    private RegisteredProgramRepository registeredProgramRepository;
    private ProgramRegistrationMapper programRegistrationMapper;

    @Autowired
    public RegisteredProgramServiceImpl(InstitutionRepository institutionRepository,
                                        RegisteredProgramRepository registeredProgramRepository,
                                        ProgramRegistrationMapper programRegistrationMapper) {
        this.institutionRepository = institutionRepository;
        this.registeredProgramRepository = registeredProgramRepository;
        this.programRegistrationMapper = programRegistrationMapper;
    }


    @Override
    public List<RegisteredProgramDto> getAllRegisteredProgramsByCourseSectorAndInstitutionClassification(Sector sector, InstitutionClassification institutionClassification) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (sector != Sector.ALL) {
            booleanBuilder.and(QRegisteredProgram.registeredProgram.sector.eq(sector));
        }
        booleanBuilder.and(QRegisteredProgram.registeredProgram.isClosed.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.isDeleted.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.courseStatus.ne(CourseStatus.BUNDLED_PROGRAM));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.institution.institutionClassification.eq(institutionClassification));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.institution.isDeleted.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.institution.institutionType.eq(InstitutionType.PUBLIC));

        Predicate predicate = booleanBuilder.getValue();

        List<RegisteredProgram> registeredProgramList = (List<RegisteredProgram>) registeredProgramRepository.findAll(predicate);

        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramList.stream()
                .map(registeredProgram -> programRegistrationMapper.registeredProgramToDto(registeredProgram))
                .collect(Collectors.toList());

        return sortRegisteredPrograms(registeredProgramDtoList, SortOrder.ASC);
    }

    @Override
    public List<RegisteredProgramDto> getAllRegisteredProgramsByNameAndSectorAndCourseName(String[] institutionNames,
                                                                                           Sector sector,
                                                                                           String courseName) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanBuilder institutionsPredicate = new BooleanBuilder();
        Arrays.asList(institutionNames)
                .forEach(name -> {
                    if (!name.equals(ALL)) {
                        institutionsPredicate.or(QRegisteredProgram.registeredProgram.institution.name.equalsIgnoreCase(name));
                    }
                });
        booleanBuilder.and(institutionsPredicate);
        booleanBuilder.and(QRegisteredProgram.registeredProgram.institution.isDeleted.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.institution.institutionClassification.eq(InstitutionClassification.TESDA));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.isDeleted.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.isClosed.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.courseStatus.ne(CourseStatus.BUNDLED_PROGRAM));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.name.containsIgnoreCase(courseName.trim()));
        if (sector != Sector.ALL) {
            booleanBuilder.and(QRegisteredProgram.registeredProgram.sector.eq(sector));
        }

        Predicate predicate = booleanBuilder.getValue();

        List<RegisteredProgram> registeredProgramList = (List<RegisteredProgram>) registeredProgramRepository.findAll(predicate);

        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramList.stream()
                .map(registeredProgram -> programRegistrationMapper.registeredProgramToDto(registeredProgram))
                .collect(Collectors.toList());

        return sortRegisteredPrograms(registeredProgramDtoList, SortOrder.ASC);
    }

    @Override
    public List<RegisteredProgramDto> getAllRegisteredProgramsWithFilter(RegisteredProgramFilter registeredProgramFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanBuilder operatingUnitTypePredicate = new BooleanBuilder();
        Arrays.asList(registeredProgramFilter.getOperatingUnitType())
                .forEach(operatingUnitType -> {
                    if (!operatingUnitType.equals(OperatingUnitType.TOTAL)) {
                        operatingUnitTypePredicate.or(QRegisteredProgram.registeredProgram.institution.operatingUnitType.eq(operatingUnitType));
                    }
                });

        booleanBuilder.and(operatingUnitTypePredicate);
        BooleanBuilder institutionClassificationPredicate = new BooleanBuilder();
        Arrays.asList(registeredProgramFilter.getInstitutionClassification())
                .forEach(institutionClassification -> {
                    if (!institutionClassification.equals(InstitutionClassification.ALL)) {
                        institutionClassificationPredicate.or(QRegisteredProgram.registeredProgram.institution.institutionClassification.eq(institutionClassification));
                    }
                });

        booleanBuilder.and(institutionClassificationPredicate);
        BooleanBuilder institutionsPredicate = new BooleanBuilder();
        Arrays.asList(registeredProgramFilter.getInstitutionIds())
                .forEach(id -> {
                    if (id != 0) {
                        institutionsPredicate.or(QRegisteredProgram.registeredProgram.institution.id.eq(id));
                    }
                });

        booleanBuilder.and(institutionsPredicate);
        booleanBuilder.and(QRegisteredProgram.registeredProgram.institution.isDeleted.eq(false));

        booleanBuilder.and(QRegisteredProgram.registeredProgram.isDeleted.eq(false));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.isClosed.eq(registeredProgramFilter.getIsClosed()));

        if (registeredProgramFilter.getCourseStatus() != CourseStatus.ALL) {
            booleanBuilder.and(QRegisteredProgram.registeredProgram.courseStatus.eq(registeredProgramFilter.getCourseStatus()));
        }

        if (registeredProgramFilter.getSector() != Sector.ALL) {
            booleanBuilder.and(QRegisteredProgram.registeredProgram.sector.eq(registeredProgramFilter.getSector()));
        }

        booleanBuilder.and(QRegisteredProgram.registeredProgram.name.toLowerCase().trim()
                .containsIgnoreCase(registeredProgramFilter.getCourseName().trim()));
        booleanBuilder.and(QRegisteredProgram.registeredProgram.programRegistrationNumber.toLowerCase().trim()
                .containsIgnoreCase(registeredProgramFilter.getRegisteredProgramNumber().trim()));


        if (registeredProgramFilter.getDateIssuedFrom() != null) {
            booleanBuilder.and(QRegisteredProgram.registeredProgram.dateIssued
                    .goe(ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgramFilter.getDateIssuedFrom())));
        }

        if (registeredProgramFilter.getDateIssuedTo() != null) {
            booleanBuilder.and(QRegisteredProgram.registeredProgram.dateIssued
                    .loe(ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgramFilter.getDateIssuedTo())));
        }

        Predicate predicate = booleanBuilder.getValue();

        List<RegisteredProgram> registeredProgramList = (List<RegisteredProgram>) registeredProgramRepository.findAll(predicate);


        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramList.stream()
                .map(registeredProgram -> programRegistrationMapper.registeredProgramToDto(registeredProgram))
                .collect(Collectors.toList());

        return sortRegisteredPrograms(registeredProgramDtoList, registeredProgramFilter.getSortOrder());
    }

    private List<RegisteredProgramDto> sortRegisteredPrograms(List<RegisteredProgramDto> registeredProgramDtoList, SortOrder sortOrder) {
        if (sortOrder.equals(SortOrder.ASC)) {
            registeredProgramDtoList.sort(Comparator.comparing(RegisteredProgramDto::getDateIssued));
        } else {
            registeredProgramDtoList.sort(Collections.reverseOrder(Comparator.comparing(RegisteredProgramDto::getDateIssued)));
        }
        return registeredProgramDtoList;
    }

    @Override
    public ProgramRegistrationWrapper getCourseCountPerInstitution() {
        List<Institution> institutions = institutionRepository
                .findAllByInstitutionTypeAndInstitutionClassification(InstitutionType.PUBLIC, InstitutionClassification.TESDA);
        List<InstitutionDto> institutionDtos = institutions
                .stream()
                .filter(institution -> !institution.getIsDeleted())
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
            if (sector.sectorType.equals("TTI")  && sector != Sector.ALL) {
                CourseCount courseCount = new CourseCount();
                courseCount.setSector(sector);
                total.getCourseCountList().add(courseCount);
            }
        });
        //

        institutionDtos.forEach(
                institutionDto -> {
                    InstitutionWrapper institutionWrapper = new InstitutionWrapper();
                    institutionWrapper.setOperatingUnitType(institutionDto.getOperatingUnitType());
                    institutionWrapper.setInstitutionName(institutionDto.getName());
                    institutionWrapper.setInstitutionShortName(institutionDto.getShortName());

                    Arrays.asList(Sector.values()).forEach(sector -> {
                        if (sector.sectorType.equals("TTI") && sector != Sector.ALL) {
                            CourseCount courseCount = new CourseCount();
                            courseCount.setSector(sector);
                            institutionDto.getRegisteredPrograms()
                                    .forEach(programDto -> {
                                        if (sector.equals(programDto.getSector()) &&
                                                !programDto.getIsDeleted() &&
                                                !programDto.getIsClosed() &&
                                                !programDto.getCourseStatus().equals(CourseStatus.BUNDLED_PROGRAM)) {
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
                        }
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

    @Override
    @Transactional
    public RegisteredProgram createRegisteredProgram(RegisteredProgramRequestDto registeredProgramDto) {
        Institution institution = institutionRepository.getOne(registeredProgramDto.getInstitutionId());
        RegisteredProgram registeredProgram = programRegistrationMapper.registeredProgramToEntity(registeredProgramDto);
        registeredProgram.setInstitution(institution);
        registeredProgram.setIsClosed(false);
        registeredProgram.setIsDeleted(false);
        registeredProgram.setDateIssued(ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgramDto.getDateIssued()));
        institution.getRegisteredPrograms().add(registeredProgram);
        Institution savedInstitution = institutionRepository.save(institution);
        return savedInstitution.getRegisteredPrograms()
                .stream()
                .max(Comparator.comparing(RegisteredProgram::getId))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveRegisteredProgramRequirements(RegisteredProgramRequestDto registeredProgramRequestDto) {
        RegisteredProgram registeredProgram = registeredProgramRepository.findById(registeredProgramRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        registeredProgram = programRegistrationMapper.updatedRegisteredProgramRequirementsToEntity(registeredProgramRequestDto,registeredProgram);
        RegisteredProgram finalRegisteredProgram = registeredProgram;
        registeredProgram.getNonTeachingStaffList().forEach(
                nonTeachingStaff -> nonTeachingStaff.setRegisteredProgram(finalRegisteredProgram)
        );
        registeredProgram.getOfficialList().forEach(
                official -> official.setRegisteredProgram(finalRegisteredProgram)
        );
        registeredProgram.getTrainerList().forEach(
                trainer -> trainer.setRegisteredProgram(finalRegisteredProgram)
        );
        registeredProgramRepository.save(registeredProgram);
    }

    @Override
    @Transactional
    public void updateRegisteredProgram(RegisteredProgramRequestDto registeredProgramRequestDto) {
        RegisteredProgram registeredProgram = registeredProgramRepository.findById(registeredProgramRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        registeredProgram = programRegistrationMapper.updatedRegisteredProgramToEntity(registeredProgramRequestDto, registeredProgram);
        registeredProgram.setDateIssued(ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgramRequestDto.getDateIssued()));
        registeredProgram.setUpdatedDate(LocalDateTime.now());
        registeredProgramRepository.save(registeredProgram);
    }

    @Override
    public RegisteredProgramRequestDto getRegisteredProgramDto(Long id) {
        RegisteredProgram registeredProgram = registeredProgramRepository.getOne(id);
        RegisteredProgramRequestDto registeredProgramRequestDto = programRegistrationMapper
                .registeredProgramToRequestDto(registeredProgram);
        registeredProgramRequestDto.setNonTeachingStaffDtoList(
                registeredProgramRequestDto.getNonTeachingStaffDtoList()
                        .stream()
                        .filter(nonTeachingStaffDto -> !nonTeachingStaffDto.getIsDeleted())
                        .collect(Collectors.toList())
        );
        registeredProgramRequestDto.setOfficialDtoList(
                registeredProgramRequestDto.getOfficialDtoList()
                        .stream()
                        .filter(officialDto -> !officialDto.getIsDeleted())
                        .collect(Collectors.toList())
        );
        registeredProgramRequestDto.setTrainerDtoList(
                registeredProgramRequestDto.getTrainerDtoList()
                        .stream()
                        .filter(trainerDto -> !trainerDto.getIsDeleted())
                        .collect(Collectors.toList())
        );
        return registeredProgramRequestDto;
    }

    @Override
    @Transactional
    public void deleteRegisteredProgram(Long id) {
        RegisteredProgram registeredProgram = registeredProgramRepository.getOne(id);
        registeredProgram.setIsDeleted(true);
        registeredProgram.setUpdatedDate(LocalDateTime.now());
        registeredProgramRepository.save(registeredProgram);
    }
}
