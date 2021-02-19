package com.tesda8.region8.certification.service;

import com.google.common.base.Strings;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.certification.model.dto.ExpiredCertificateDetails;
import com.tesda8.region8.certification.model.dto.ExpiredCertificateWrapper;
import com.tesda8.region8.certification.repository.CertificateRepository;
import com.tesda8.region8.program.registration.model.entities.QTrainer;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import com.tesda8.region8.program.registration.repository.TrainerRepository;

import com.tesda8.region8.util.service.ApplicationUtil;
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
    public ExpiredCertificateWrapper getExpiredCertificates(String trainerName) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(QTrainer.trainer.fullName.containsIgnoreCase(Strings.isNullOrEmpty(trainerName) ? "" : trainerName));

        Predicate predicate = booleanBuilder.getValue();

        List<Trainer> trainerList = (List<Trainer>) trainerRepository.findAll(predicate);
        ExpiredCertificateWrapper expiredCertificateWrapper = new ExpiredCertificateWrapper();
        trainerList.forEach(
                trainer -> {
                    checkExpiredCertificates(expiredCertificateWrapper, trainer);
                }
        );
        return expiredCertificateWrapper;
    }

    @Cacheable("expiredCertificatesCount")
    public long expiredCertificatesCount() {
        return getExpiredCertificates("").getTotalCount();
    }

    private void checkExpiredCertificates(ExpiredCertificateWrapper expiredCertificateWrapper, Trainer trainer) {
        ExpiredCertificateDetails expiredCertificateDetails = new ExpiredCertificateDetails();
        expiredCertificateDetails.setTrainerName(trainer.getFullName());
        expiredCertificateDetails.setId(trainer.getId());
        trainer.getCertificates().forEach(
                certificate -> {
                    if (certificate.getExpirationDate() != null &&
                            !certificate.getIsDeleted() &&
                            (certificate.getExpirationDate().isEqual(ApplicationUtil.getLocalDateTimeNow()) ||
                             certificate.getExpirationDate().isBefore(ApplicationUtil.getLocalDateTimeNow()))) {

                        switch (certificate.getCertificateType()) {
                            case NC:
                                expiredCertificateDetails.setExpiredNC(true);
                                break;
                            case NTTC:
                                expiredCertificateDetails.setExpiredNTTC(true);
                                break;
                            case TM:
                                expiredCertificateDetails.setExpiredTMC(true);
                                break;
                            default:
                                break;
                        }
                    }
                }
        );
        if (expiredCertificateDetails.getExpiredNC() ||
                expiredCertificateDetails.getExpiredNTTC() ||
                expiredCertificateDetails.getExpiredTMC()) {
            expiredCertificateWrapper.getExpiredTrainerCertificates().add(expiredCertificateDetails);
        }
    }
}
