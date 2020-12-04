package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.ExpiredDocumentsWrapper;
import com.tesda8.region8.program.registration.model.dto.ExpiredRegisteredProgramDocument;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.MoaValidityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public ExpiredDocumentsWrapper getExpiredDocuments() {
        List<RegisteredProgram> registeredProgramList = registeredProgramRepository.findAll();
        ExpiredDocumentsWrapper expiredDocumentsWrapper = new ExpiredDocumentsWrapper();
        registeredProgramList.forEach(
                registeredProgram -> {
                    if (registeredProgram.getRegistrationRequirement() != null) {
                        if (registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued() != null) {
                            checkIfBuildingOwnershipIsExpired(expiredDocumentsWrapper, registeredProgram);
                        }
                        if (registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued() != null) {
                            checkIfFireSafetyIsExpired(expiredDocumentsWrapper, registeredProgram);
                        }
                        if (registeredProgram.getRegistrationRequirement().getMoaValidity() != null) {
                            checkIfMoaValidityIsExpired(expiredDocumentsWrapper, registeredProgram);
                        }
                    }
                }
        );
        return expiredDocumentsWrapper;
    }

    private void checkIfBuildingOwnershipIsExpired(ExpiredDocumentsWrapper expiredDocumentsWrapper, RegisteredProgram registeredProgram) {
        LocalDateTime expirationDate = convertToLocalDateTimeViaSqlTimestamp(registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued()).plusYears(BUILDING_OWNERSHIP_EXPIRATION_YEARS);
        if (Date.valueOf(LocalDate.now()).after(convertToDateViaInstant(expirationDate))) {
            expiredDocumentsWrapper.getExpiredBuildingOwnership().add(new ExpiredRegisteredProgramDocument(registeredProgram.getProgramRegistrationNumber(), registeredProgram.getId()));
        }
    }

    private void checkIfFireSafetyIsExpired(ExpiredDocumentsWrapper expiredDocumentsWrapper, RegisteredProgram registeredProgram) {
        LocalDateTime expirationDate = convertToLocalDateTimeViaSqlTimestamp(registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued()).plusYears(FIRE_SAFETY_EXPIRATION_YEARS);
        if (Date.valueOf(LocalDate.now()).after(convertToDateViaInstant(expirationDate))) {
            expiredDocumentsWrapper.getExpiredFireSafety().add(new ExpiredRegisteredProgramDocument(registeredProgram.getProgramRegistrationNumber(), registeredProgram.getId()));
        }
    }

    private void checkIfMoaValidityIsExpired(ExpiredDocumentsWrapper expiredDocumentsWrapper, RegisteredProgram registeredProgram) {
        if (!registeredProgram.getRegistrationRequirement().getMoaValidityType().equals(MoaValidityType.OPEN)) {
            LocalDateTime expirationDate = convertToLocalDateTimeViaSqlTimestamp(registeredProgram.getRegistrationRequirement().getMoaValidity())
                    .plusYears(Integer.parseInt(registeredProgram.getRegistrationRequirement().getMoaValidityType().label));
            if (Date.valueOf(LocalDate.now()).after(convertToDateViaInstant(expirationDate))) {
                expiredDocumentsWrapper.getExpiredMoaValidity().add(new ExpiredRegisteredProgramDocument(registeredProgram.getProgramRegistrationNumber(), registeredProgram.getId()));
            }
        }
    }

    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(java.util.Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

    public java.util.Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
