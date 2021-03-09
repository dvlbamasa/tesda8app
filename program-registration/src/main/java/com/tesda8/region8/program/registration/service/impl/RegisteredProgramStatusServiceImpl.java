package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.ExpiredDocumentsWrapper;
import com.tesda8.region8.program.registration.model.dto.ExpiredRegisteredProgramDocument;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.ExpiredDocumentType;
import com.tesda8.region8.util.enums.MoaValidityType;
import com.tesda8.region8.util.service.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisteredProgramStatusServiceImpl implements RegisteredProgramStatusService {

    private RegisteredProgramRepository registeredProgramRepository;
    public static final int BUILDING_OWNERSHIP_EXPIRATION_YEARS = 2;
    public static final int FIRE_SAFETY_EXPIRATION_YEARS = 1;

    @Autowired
    public RegisteredProgramStatusServiceImpl(RegisteredProgramRepository registeredProgramRepository) {
        this.registeredProgramRepository = registeredProgramRepository;
    }

    @Override
    @Cacheable("expiredDocumentsCount")
    public long getExpiredDocumentsCount() {
        return getExpiredDocuments(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), ExpiredDocumentType.ALL).getTotalCount();
    }

    @Override
    @Cacheable("expiredDocuments")
    public ExpiredDocumentsWrapper getExpiredDocuments(int pageNumber, int pageSize, ExpiredDocumentType expiredDocumentType) {
        List<RegisteredProgram> registeredProgramList = registeredProgramRepository.findAll();
        ExpiredDocumentsWrapper expiredDocumentsWrapper = new ExpiredDocumentsWrapper();
        registeredProgramList.forEach(
                registeredProgram -> {
                    ExpiredRegisteredProgramDocument expiredRegisteredProgramDocument = new ExpiredRegisteredProgramDocument();
                    expiredRegisteredProgramDocument.setProgramRegistrationNumber(registeredProgram.getProgramRegistrationNumber());
                    expiredRegisteredProgramDocument.setId(registeredProgram.getId());
                    expiredRegisteredProgramDocument.setQualification(registeredProgram.getName());
                    expiredRegisteredProgramDocument.setInstitutionName(registeredProgram.getInstitution().getName());
                    if (registeredProgram.getRegistrationRequirement() != null) {
                        if (registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued() != null) {
                            expiredRegisteredProgramDocument.setExpiredBuildingOwnership(checkIfBuildingOwnershipIsExpired(registeredProgram));
                        }
                        if (registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued() != null) {
                            expiredRegisteredProgramDocument.setExpiredFireSafety(checkIfFireSafetyIsExpired(registeredProgram));
                        }
                        if (registeredProgram.getRegistrationRequirement().getMoaValidity() != null) {
                            expiredRegisteredProgramDocument.setExpiredMoaValidity(checkIfMoaValidityIsExpired(registeredProgram));
                        }
                    }
                    switch (expiredDocumentType) {
                        case FIRE_SAFETY:
                            if (expiredRegisteredProgramDocument.isExpiredFireSafety()) {
                                expiredDocumentsWrapper.getExpiredRegisteredProgramDocuments().add(expiredRegisteredProgramDocument);
                            }
                            break;
                        case BUILDING_OWNERSHIP:
                            if (expiredRegisteredProgramDocument.isExpiredBuildingOwnership()) {
                                expiredDocumentsWrapper.getExpiredRegisteredProgramDocuments().add(expiredRegisteredProgramDocument);
                            }
                            break;
                        case MOA_VALIDITY:
                            if (expiredRegisteredProgramDocument.isExpiredMoaValidity()) {
                                expiredDocumentsWrapper.getExpiredRegisteredProgramDocuments().add(expiredRegisteredProgramDocument);
                            }
                            break;
                        case ALL:
                            if (expiredRegisteredProgramDocument.isExpiredBuildingOwnership() ||
                                    expiredRegisteredProgramDocument.isExpiredFireSafety() ||
                                    expiredRegisteredProgramDocument.isExpiredMoaValidity()) {
                                expiredDocumentsWrapper.getExpiredRegisteredProgramDocuments().add(expiredRegisteredProgramDocument);
                            }
                            break;
                        default:
                            break;
                    }
                }
        );

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), expiredDocumentsWrapper.getExpiredRegisteredProgramDocuments().size());

        Page<ExpiredRegisteredProgramDocument> expiredRegisteredProgramDocumentPage =
                new PageImpl<>(
                        expiredDocumentsWrapper.getExpiredRegisteredProgramDocuments().subList(start, end), pageable,
                        expiredDocumentsWrapper.getTotalCount()
                );
        expiredDocumentsWrapper.setExpiredRegisteredProgramDocumentPage(expiredRegisteredProgramDocumentPage);
        return expiredDocumentsWrapper;
    }

    private boolean checkIfBuildingOwnershipIsExpired(RegisteredProgram registeredProgram) {
        LocalDateTime expirationDate = ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued()).plusYears(BUILDING_OWNERSHIP_EXPIRATION_YEARS);
        return Date.valueOf(LocalDate.now()).after(ApplicationUtil.convertToDateViaInstant(expirationDate));
    }

    private boolean checkIfFireSafetyIsExpired(RegisteredProgram registeredProgram) {
        LocalDateTime expirationDate = ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued()).plusYears(FIRE_SAFETY_EXPIRATION_YEARS);
        return Date.valueOf(LocalDate.now()).after(ApplicationUtil.convertToDateViaInstant(expirationDate));
    }

    private boolean checkIfMoaValidityIsExpired(RegisteredProgram registeredProgram) {
        if (!registeredProgram.getRegistrationRequirement().getMoaValidityType().equals(MoaValidityType.OPEN)) {
            LocalDateTime expirationDate = ApplicationUtil.convertToLocalDateTimeViaInstant(registeredProgram.getRegistrationRequirement().getMoaValidity())
                    .plusYears(Integer.parseInt(registeredProgram.getRegistrationRequirement().getMoaValidityType().label));
            return Date.valueOf(LocalDate.now()).after(ApplicationUtil.convertToDateViaInstant(expirationDate));
        }
        return false;
    }
}
