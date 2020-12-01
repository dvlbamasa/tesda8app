package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.ExpiredStatusCount;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RegisteredProgramStatusServiceImpl implements RegisteredProgramStatusService {

    private RegisteredProgramRepository registeredProgramRepository;

    @Autowired
    public RegisteredProgramStatusServiceImpl(RegisteredProgramRepository registeredProgramRepository) {
        this.registeredProgramRepository = registeredProgramRepository;
    }

    @Override
    public ExpiredStatusCount getStatusCount() {
        ExpiredStatusCount expiredStatusCount = new ExpiredStatusCount();
        expiredStatusCount.setMoaValidity(0L);
        expiredStatusCount.setExpiredFireSafety(0L);
        expiredStatusCount.setExpiredBuildingOwnership(0L);
        List<RegisteredProgram> registeredProgramList = registeredProgramRepository.findAll();
        registeredProgramList.forEach(
                registeredProgram -> {
                    if (registeredProgram.getRegistrationRequirement() != null) {
                        if (registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued() != null &&
                                (registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued().before(Date.valueOf(LocalDate.now())) ||
                                        registeredProgram.getRegistrationRequirement().getBuildingOwnershipDateIssued().equals(Date.valueOf(LocalDate.now())))) {
                            expiredStatusCount.setExpiredBuildingOwnership(expiredStatusCount.getExpiredBuildingOwnership() + 1);
                        }
                        if (registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued() != null &&
                                (registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued().before(Date.valueOf(LocalDate.now())) ||
                                        registeredProgram.getRegistrationRequirement().getFireSafetyDateIssued().equals(Date.valueOf(LocalDate.now())))) {
                            expiredStatusCount.setExpiredFireSafety(expiredStatusCount.getExpiredFireSafety() + 1);
                        }
                        if (registeredProgram.getRegistrationRequirement().getMoaValidity() != null &&
                                (registeredProgram.getRegistrationRequirement().getMoaValidity().before(Date.valueOf(LocalDate.now())) ||
                                        registeredProgram.getRegistrationRequirement().getMoaValidity().equals(Date.valueOf(LocalDate.now())))) {
                            expiredStatusCount.setMoaValidity(expiredStatusCount.getMoaValidity() + 1);
                        }
                    }
                }
        );
        expiredStatusCount.setTotal(expiredStatusCount.getExpiredBuildingOwnership() + expiredStatusCount.getExpiredFireSafety() + expiredStatusCount.getMoaValidity());
        return expiredStatusCount;
    }
}
