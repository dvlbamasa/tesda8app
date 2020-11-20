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
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.model.wrapper.CourseCount;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionProgramRegCounter;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    private static Logger logger = LoggerFactory.getLogger(InstitutionServiceImpl.class);
    private static final String ALL = "ALL";


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
        return getInstitutionDtos(sector, institutions);
    }

    @Override
    public List<InstitutionDto> getAllInstitutionByCourseSectorAndInstitutionClassification(Sector sector, InstitutionClassification institutionClassification) {
        List<Institution> institutions = institutionRepository
                .findAllByInstitutionTypeAndInstitutionClassification(InstitutionType.PUBLIC, institutionClassification);
        return getInstitutionDtos(sector, institutions);
    }

    private List<InstitutionDto> getInstitutionDtos(Sector sector, List<Institution> institutions) {
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
                                    .filter(registeredProgramDto -> !registeredProgramDto.getIsDeleted())                                    .filter(registeredProgramDto -> !registeredProgramDto.getIsDeleted())
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
    public List<InstitutionDto> getAllInstitutionByNameAndSectorAndCourseName(String[] institutionNames,
                                                                              Sector sector,
                                                                              String courseName) {
        List<Institution> institutions = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = Lists.newArrayList();
        Arrays.stream(institutionNames).forEach(
                institutionName -> {
                    List<InstitutionDto> institutionList = institutions
                            .stream()
                            .filter(institution -> !institution.getIsDeleted())
                            .filter(institution -> institutionName.equals(ALL) || institution.getName().equalsIgnoreCase(institutionName))
                            .filter(institution -> institution.getInstitutionClassification().equals(InstitutionClassification.TESDA))
                            .map(institution -> programRegistrationMapper.institutionToDto(institution))
                            .collect(Collectors.toList());
                    institutionDtos.addAll(institutionList);
                }
        );
        institutionDtos.forEach(
                institutionDto -> {
                    institutionDto.setRegisteredPrograms(
                            institutionDto.getRegisteredPrograms()
                                    .stream()
                                    .filter(programDto -> !programDto.getIsDeleted())
                                    .filter(programDto -> !programDto.getIsClosed())
                                    .filter(programDto -> !programDto.getCourseStatus().equals(CourseStatus.BUNDLED_PROGRAM))
                                    .filter(programDto -> sector.equals(Sector.ALL) ||  programDto.getSector().equals(sector))
                                    .filter(programDto -> programDto.getName().toLowerCase().contains(courseName.toLowerCase()))
                                    .collect(Collectors.toList())
                    );
                }
        );
        return institutionDtos;
    }

    @Override
    public List<RegisteredProgramDto> getAllRegisteredProgramsWithFilter(RegisteredProgramFilter registeredProgramFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanBuilder operatingUnitTypePredicate = new BooleanBuilder();
        Arrays.asList(registeredProgramFilter.getOperatingUnitType())
                .forEach(operatingUnitType -> {
                    if (!operatingUnitType.equals(OperatingUnitType.TOTAL)) {
                        operatingUnitTypePredicate.or(QInstitution.institution.operatingUnitType.eq(operatingUnitType));
                    }
                });

        booleanBuilder.and(operatingUnitTypePredicate);
        BooleanBuilder institutionClassificationPredicate = new BooleanBuilder();
        Arrays.asList(registeredProgramFilter.getInstitutionClassification())
                .forEach(institutionClassification -> {
                    if (!institutionClassification.equals(InstitutionClassification.ALL)) {
                        institutionClassificationPredicate.or(QInstitution.institution.institutionClassification.eq(institutionClassification));
                    }
                });

        booleanBuilder.and(institutionClassificationPredicate);
        BooleanBuilder institutionsPredicate = new BooleanBuilder();
        Arrays.asList(registeredProgramFilter.getInstitutionIds())
                .forEach(id -> {
                    if (id != 0) {
                        institutionsPredicate.or(QInstitution.institution.id.eq(id));
                    }
                });

        booleanBuilder.and(institutionsPredicate);
        booleanBuilder.and(QInstitution.institution.isDeleted.eq(false));

        Predicate predicate = booleanBuilder.getValue();

        List<Institution> institutions = (List<Institution>) institutionRepository.findAll(predicate);
        List<InstitutionDto> institutionDtos = institutions.stream()
                .map(institution -> programRegistrationMapper.institutionToDto(institution))
                .collect(Collectors.toList());

        institutionDtos.forEach(
                institutionDto -> {
                    institutionDto.setRegisteredPrograms(
                            institutionDto.getRegisteredPrograms()
                                    .stream()
                                    .filter(programDto -> !programDto.getIsDeleted())
                                    .filter(programDto -> registeredProgramFilter.getSector().equals(Sector.ALL) || programDto.getSector().equals(registeredProgramFilter.getSector()))
                                    .filter(programDto -> programDto.getProgramRegistrationNumber().toLowerCase().trim().contains(registeredProgramFilter.getRegisteredProgramNumber().toLowerCase().trim()))
                                    .filter(programDto -> programDto.getName().toLowerCase().trim().contains(registeredProgramFilter.getCourseName().toLowerCase()))
                                    .filter(programDto -> registeredProgramFilter.getCourseStatus().equals(CourseStatus.ALL) || programDto.getCourseStatus().equals(registeredProgramFilter.getCourseStatus()))
                                    .filter(programDto -> programDto.getIsClosed().equals(registeredProgramFilter.getIsClosed()))
                                    .collect(Collectors.toList())
                    );
                }
        );

        if (registeredProgramFilter.getDateIssuedFrom() != null) {
            institutionDtos.forEach(
                    institutionDto -> {
                        institutionDto.setRegisteredPrograms(
                                institutionDto.getRegisteredPrograms()
                                        .stream()
                                        .filter(programDto -> programDto.getDateIssued().isEqual(convertToLocalDateTimeViaInstant(registeredProgramFilter.getDateIssuedFrom())) ||
                                                              programDto.getDateIssued().isAfter(convertToLocalDateTimeViaInstant(registeredProgramFilter.getDateIssuedFrom())))
                                        .collect(Collectors.toList())
                        );
                    }
            );
        }

        if (registeredProgramFilter.getDateIssuedTo() != null) {
            institutionDtos.forEach(
                    institutionDto -> {
                        institutionDto.setRegisteredPrograms(
                                institutionDto.getRegisteredPrograms()
                                        .stream()
                                        .filter(programDto -> programDto.getDateIssued().isEqual(convertToLocalDateTimeViaInstant(registeredProgramFilter.getDateIssuedTo())) ||
                                                              programDto.getDateIssued().isBefore(convertToLocalDateTimeViaInstant(registeredProgramFilter.getDateIssuedTo())))
                                        .collect(Collectors.toList())
                        );
                    }
            );
        }
        List<RegisteredProgramDto> registeredProgramDtoList = Lists.newArrayList();

        institutionDtos.forEach(
                institutionDto -> {
                    institutionDto.getRegisteredPrograms().forEach(
                            registeredProgramDto -> {
                                registeredProgramDto.setInstitutionName(institutionDto.getName());
                                registeredProgramDto.setInstitutionClassification(institutionDto.getInstitutionClassification().label);
                                registeredProgramDto.setOperatingUnit(institutionDto.getOperatingUnitType().label);
                                registeredProgramDtoList.add(registeredProgramDto);
                            }
                    );
                }
        );

        return sortRegisteredPrograms(registeredProgramDtoList, registeredProgramFilter.getSortOrder());
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private List<RegisteredProgramDto> sortRegisteredPrograms(List<RegisteredProgramDto> registeredProgramDtoList, SortOrder sortOrder) {
        if (sortOrder.equals(SortOrder.ASC)) {
            registeredProgramDtoList.sort(Comparator.comparing(RegisteredProgramDto::getDateIssued));
        } else {
            registeredProgramDtoList.sort(Collections.reverseOrder(Comparator.comparing(RegisteredProgramDto::getDateIssued)));
        }
        return registeredProgramDtoList;
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
    public void createRegisteredProgram(RegisteredProgramRequestDto registeredProgramDto) {
        Institution institution = institutionRepository.getOne(registeredProgramDto.getInstitutionId());
        RegisteredProgram registeredProgram = programRegistrationMapper.registeredProgramToEntity(registeredProgramDto);
        registeredProgram.setInstitution(institution);
        registeredProgram.setIsClosed(false);
        registeredProgram.setIsDeleted(false);
        registeredProgram.setDateIssued(convertToLocalDateTimeViaInstant(registeredProgramDto.getDateIssued()));
        institution.getRegisteredPrograms().add(registeredProgram);
        institutionRepository.save(institution);
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
    public void updateRegisteredProgram(RegisteredProgramRequestDto registeredProgramRequestDto) {
        RegisteredProgram registeredProgram = registeredProgramRepository.findById(registeredProgramRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        registeredProgram = programRegistrationMapper.updatedRegisteredProgramToEntity(registeredProgramRequestDto, registeredProgram);
        registeredProgram.setDateIssued(convertToLocalDateTimeViaInstant(registeredProgramRequestDto.getDateIssued()));
        registeredProgram.setUpdatedDate(LocalDateTime.now());
        registeredProgramRepository.save(registeredProgram);
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
    public RegisteredProgramRequestDto getRegisteredProgramDto(Long id) {
        RegisteredProgram registeredProgram = registeredProgramRepository.getOne(id);
        return programRegistrationMapper.registeredProgramToRequestDto(registeredProgram);
    }

    @Override
    public InstitutionDto getInstitutionDto(Long id) {
        Institution institution = institutionRepository.getOne(id);
        return programRegistrationMapper.institutionToDto(institution);
    }

    @Override
    @Transactional
    public void deleteRegisteredProgram(Long id) {
        RegisteredProgram registeredProgram = registeredProgramRepository.getOne(id);
        registeredProgram.setIsDeleted(true);
        registeredProgram.setUpdatedDate(LocalDateTime.now());
        registeredProgramRepository.save(registeredProgram);
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
