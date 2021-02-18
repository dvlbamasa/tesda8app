package com.tesda8.region8.certification.service;

import com.tesda8.region8.certification.repository.CertificateRepository;
import com.tesda8.region8.program.registration.model.dto.CertificateDto;
import com.tesda8.region8.program.registration.model.entities.Certificate;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.repository.TrainerRepository;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.util.service.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Service
@Qualifier("certificate")
@RequiredArgsConstructor
public class CertificateServiceImpl implements RegistrationRequirementsCrudService<CertificateDto> {

    private static Logger logger = LoggerFactory.getLogger(CertificateServiceImpl.class);


    private final TrainerRepository trainerRepository;
    private final CertificateRepository certificateRepository;
    private final ProgramRegistrationMapper programRegistrationMapper;

    @Override
    @Transactional
    @CacheEvict(value={"expiredCertificatesCount", "expiredCertificates"}, allEntries=true)
    public void create(CertificateDto dto) {
        Trainer trainer = trainerRepository.findById(dto.getTrainerId()).orElseThrow(EntityNotFoundException::new);
        Certificate certificate = programRegistrationMapper.certificateToEntity(dto);
        certificate.setDateIssued(ApplicationUtil.convertToLocalDateTimeViaInstant(dto.getDateIssuedRequest()));
        certificate.setExpirationDate(ApplicationUtil.convertToLocalDateTimeViaInstant(dto.getExpirationDateRequest()));
        certificate.setTrainer(trainer);
        certificate.setIsDeleted(false);
        trainer.getCertificates().add(certificate);
        certificateRepository.save(certificate);
    }

    @Override
    @Transactional
    @CacheEvict(value={"expiredCertificatesCount", "expiredCertificates"}, allEntries=true)
    public void update(CertificateDto dto) {
        Certificate certificate = certificateRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        programRegistrationMapper.updatedCertificate(dto, certificate);
        certificate.setDateIssued(ApplicationUtil.convertToLocalDateTimeViaInstant(dto.getDateIssuedRequest()));
        certificate.setExpirationDate(ApplicationUtil.convertToLocalDateTimeViaInstant(dto.getExpirationDateRequest()));
        certificateRepository.save(certificate);
    }

    @Override
    @Transactional
    @CacheEvict(value={"expiredCertificatesCount", "expiredCertificates"}, allEntries=true)
    public void delete(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        certificate.setIsDeleted(true);
        certificateRepository.save(certificate);
    }

    @Override
    public CertificateDto get(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        CertificateDto certificateDto = programRegistrationMapper.certificateToDto(certificate);
        certificateDto.setDateIssuedRequest(ApplicationUtil.convertToDateViaInstant(certificate.getDateIssued()));
        certificateDto.setExpirationDateRequest(ApplicationUtil.convertToDateViaInstant(certificate.getExpirationDate()));
        return certificateDto;
    }
}
