package com.tesda8.region8.certification.service;

import com.tesda8.region8.certification.model.dto.ExpiredCertificateDetails;
import com.tesda8.region8.certification.model.dto.ExpiredCertificateWrapper;
import com.tesda8.region8.certification.repository.CertificateRepository;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import com.tesda8.region8.program.registration.repository.TrainerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpiredCertificateService {

    private static Logger logger = LoggerFactory.getLogger(ExpiredCertificateService.class);

    private TrainerRepository trainerRepository;
    private CertificateRepository certificateRepository;

    @Autowired
    public ExpiredCertificateService(TrainerRepository trainerRepository, CertificateRepository certificateRepository) {
        this.trainerRepository = trainerRepository;
        this.certificateRepository = certificateRepository;
    }

    @Cacheable("expiredCertificates")
    public ExpiredCertificateWrapper getExpiredCertificates() {
        List<Trainer> trainerList = trainerRepository.findAll();
        ExpiredCertificateWrapper expiredCertificateWrapper = new ExpiredCertificateWrapper();
        trainerList.forEach(
                trainer -> {
                    checkExpiredCertificates(expiredCertificateWrapper, trainer);
                }
        );
        expiredCertificateWrapper.setExpiredNC(expiredCertificateWrapper.getExpiredNC().stream().distinct().collect(Collectors.toList()));
        expiredCertificateWrapper.setExpiredNTTC(expiredCertificateWrapper.getExpiredNTTC().stream().distinct().collect(Collectors.toList()));
        expiredCertificateWrapper.setExpiredTMC(expiredCertificateWrapper.getExpiredTMC().stream().distinct().collect(Collectors.toList()));
        return expiredCertificateWrapper;
    }

    @Cacheable("expiredCertificatesCount")
    public long expiredCertificatesCount() {
        return getExpiredCertificates().getTotalCount();
    }

    private void checkExpiredCertificates(ExpiredCertificateWrapper expiredCertificateWrapper, Trainer trainer) {
        trainer.getCertificates().forEach(
                certificate -> {
                    if (certificate.getExpirationDate() != null &&
                            !certificate.getIsDeleted() &&
                            (certificate.getExpirationDate().isEqual(LocalDateTime.now()) ||
                             certificate.getExpirationDate().isBefore(LocalDateTime.now()))) {
                        switch (certificate.getCertificateType()) {
                            case NC:
                                expiredCertificateWrapper.getExpiredNC().add(new ExpiredCertificateDetails(trainer.getFullName(), trainer.getId()));
                                break;
                            case NTTC:
                                expiredCertificateWrapper.getExpiredNTTC().add(new ExpiredCertificateDetails(trainer.getFullName(), trainer.getId()));
                                break;
                            case TM:
                                expiredCertificateWrapper.getExpiredTMC().add(new ExpiredCertificateDetails(trainer.getFullName(), trainer.getId()));
                                break;
                            default:
                                break;
                        }
                    }
                }
        );
    }

    private long countExpired(Trainer trainer) {
        return trainer.getCertificates()
                .stream()
                .filter(certificate -> certificate.getExpirationDate() != null &&
                        (certificate.getExpirationDate().isEqual(LocalDateTime.now()) ||
                        certificate.getExpirationDate().isBefore(LocalDateTime.now())))
                .count();

    }
}
