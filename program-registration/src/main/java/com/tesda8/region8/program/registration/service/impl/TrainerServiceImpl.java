package com.tesda8.region8.program.registration.service.impl;

import com.google.common.base.Strings;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.program.registration.model.dto.CertificateLayoutData;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.dto.TrainerFilter;
import com.tesda8.region8.program.registration.model.entities.Certificate;
import com.tesda8.region8.program.registration.model.entities.QTrainer;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.repository.TrainerRepository;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.program.registration.service.TrainerService;
import com.tesda8.region8.util.enums.CertificateType;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.service.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("trainer")
public class TrainerServiceImpl implements RegistrationRequirementsCrudService<TrainerDto>, TrainerService {


    private static Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private TrainerRepository trainerRepository;
    private RegisteredProgramRepository registeredProgramRepository;
    private ProgramRegistrationMapper programRegistrationMapper;


    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository,
                              RegisteredProgramRepository registeredProgramRepository,
                              ProgramRegistrationMapper programRegistrationMapper) {
        this.trainerRepository = trainerRepository;
        this.registeredProgramRepository = registeredProgramRepository;
        this.programRegistrationMapper = programRegistrationMapper;
    }

    /**
     * Link TVET Trainer to Registered Program
     * @param dto
     */
    @Override
    @Transactional
    public void create(TrainerDto dto) {
        RegisteredProgram registeredProgram = registeredProgramRepository
                .findById(dto.getRegisteredProgramId()).orElseThrow(EntityNotFoundException::new);
        Trainer trainer = trainerRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        trainer.setRegisteredProgram(registeredProgram);
        trainer.setNatureOfAppointmentDetails(dto.getNatureOfAppointmentDetails());
        trainer.setRemarkDetails(dto.getRemarkDetails());
        trainer.setIsDeleted(false);
        registeredProgram.getTrainerList().add(trainer);
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public void update(TrainerDto dto) {
        Trainer trainer = trainerRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        programRegistrationMapper.updatedTrainerToEntity(dto,trainer);
        trainer.setBirthdate(ApplicationUtil.convertToLocalDateTimeViaInstant(dto.getBirthdateRequest()));
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    @CacheEvict(value = "trainer", allEntries = true)
    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        trainer.setIsDeleted(true);
        trainerRepository.save(trainer);
    }

    @Override
    public TrainerDto get(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TrainerDto trainerDto = programRegistrationMapper.trainerToDto(trainer);
        trainerDto.setBirthdateRequest(ApplicationUtil.convertToDateViaInstant(trainer.getBirthdate()));
        trainerDto.setNcCertificates(
                trainerDto.getCertificates()
                .stream()
                .filter(certificate -> certificate.getCertificateType().equals(CertificateType.NC))
                .filter(certificateDto -> !certificateDto.getIsDeleted())
                .collect(Collectors.toList())
        );
        trainerDto.setTmCertificates(
                trainerDto.getCertificates()
                .stream()
                .filter(certificateDto -> certificateDto.getCertificateType().equals(CertificateType.TM))
                .filter(certificateDto -> !certificateDto.getIsDeleted())
                .collect(Collectors.toList())
        );
        trainerDto.setNttcCertificates(
                trainerDto.getCertificates()
                .stream()
                .filter(certificateDto -> certificateDto.getCertificateType().equals(CertificateType.NTTC))
                .filter(certificateDto -> !certificateDto.getIsDeleted())
                .collect(Collectors.toList())
        );
        return trainerDto;
    }


    /**
     * Create TVET Trainer in Certification Feature
     * @param trainerDto
     * @return
     */
    @Override
    @Transactional
    @CacheEvict(value = "trainer", allEntries = true)
    public TrainerDto createTrainer(TrainerDto trainerDto) {
        Trainer trainer = programRegistrationMapper.trainerToEntity(trainerDto);
        trainer.setBirthdate(ApplicationUtil.convertToLocalDateTimeViaInstant(trainerDto.getBirthdateRequest()));
        trainer.setFullName(trainerDto.fetchFullName());
        trainer.setIsDeleted(false);
        return programRegistrationMapper.trainerToDto(trainerRepository.save(trainer));
    }

    @Override
    public void updateLinkedTrainer(TrainerDto trainerDto) {
        Trainer trainer = trainerRepository.findById(trainerDto.getId()).orElseThrow(EntityNotFoundException::new);
        trainer.setNatureOfAppointmentDetails(trainerDto.getNatureOfAppointmentDetails());
        trainer.setRemarkDetails(trainerDto.getRemarkDetails());
        trainerRepository.save(trainer);
    }

    @Override
    public void unlinkTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        trainer.setRegisteredProgram(null);
        trainerRepository.save(trainer);
    }

    @Override
    public List<TrainerDto> getAllTrainer() {
        return getAllTrainerByFilter(new TrainerFilter());
    }

    @Override
    @Cacheable("trainer")
    public List<TrainerDto> getAllTrainerByFilter(TrainerFilter trainerFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (trainerFilter.getFullName() != null && !Strings.isNullOrEmpty(trainerFilter.getFullName())) {
            booleanBuilder.and(QTrainer.trainer.fullName.containsIgnoreCase(trainerFilter.getFullName()));
        }

        if (trainerFilter.getSex() != null  && !trainerFilter.getSex().equals(Sex.ALL)) {
            booleanBuilder.and(QTrainer.trainer.sex.eq(trainerFilter.getSex()));
        }

        if (trainerFilter.getEducationalAttainment() != null && !trainerFilter.getEducationalAttainment().equals(EducationalAttainment.ALL)) {
            booleanBuilder.and(QTrainer.trainer.educationalAttainment.eq(trainerFilter.getEducationalAttainment()));
        }

        if (trainerFilter.getAddress() != null && !Strings.isNullOrEmpty(trainerFilter.getAddress())) {
            booleanBuilder.and(QTrainer.trainer.address.containsIgnoreCase(trainerFilter.getAddress()));
        }

        if (trainerFilter.getEmailAddress() != null && !Strings.isNullOrEmpty(trainerFilter.getEmailAddress())) {
            booleanBuilder.and(QTrainer.trainer.emailAddress.containsIgnoreCase(trainerFilter.getEmailAddress()));
        }

        if (trainerFilter.getContactNumber() != null && !Strings.isNullOrEmpty(trainerFilter.getContactNumber())) {
            booleanBuilder.and(QTrainer.trainer.contactNumber.containsIgnoreCase(trainerFilter.getContactNumber()));
        }

        booleanBuilder.and(QTrainer.trainer.isDeleted.eq(false));

        Predicate predicate = booleanBuilder.getValue();

        List<Trainer> trainers = predicate == null ? trainerRepository.findAll() :
                (List<Trainer>) trainerRepository.findAll(predicate);

        return trainers.stream()
                .map(trainer -> programRegistrationMapper.trainerToDto(trainer))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateLayoutData fetchCertificateLayout(Long trainerId, Long certificateId) {
        Trainer trainer = trainerRepository.getOne(trainerId);
        Certificate certificate = trainer.getCertificates().stream()
                .filter(certificate1 -> certificate1.getId().equals(certificateId))
                .findAny().orElseThrow(EntityNotFoundException::new);
        CertificateLayoutData certificateLayoutData = new CertificateLayoutData();
        certificateLayoutData.setCertificateNumber(certificate.getCertificateNumber());
        certificateLayoutData.setExpirationDate(ApplicationUtil.formatLocalDateTimeToString2(certificate.getExpirationDate()));
        certificateLayoutData.setIssuedOn(ApplicationUtil.formatLocalDateTimeToString2(certificate.getDateIssued()));
        certificateLayoutData.setFullName(trainer.getFullName());
        certificateLayoutData.setQualification(certificate.getQualificationTitle());
        certificateLayoutData.setLongName(ApplicationUtil.checkIfLongName(trainer.getFullName()));
        return certificateLayoutData;
    }
}
