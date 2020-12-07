package com.tesda8.region8.scholarship.service.impl;

import com.google.common.collect.Lists;
import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.scholarship.model.dto.ScholarshipWrapper;
import com.tesda8.region8.scholarship.model.entities.ScholarshipAccomplishment;
import com.tesda8.region8.scholarship.model.mapper.ScholarshipMapper;
import com.tesda8.region8.scholarship.repository.ScholarshipAccomplishmentRepository;
import com.tesda8.region8.scholarship.service.ScholarshipAccomplishmentService;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScholarshipAccomplishmentServiceImpl implements ScholarshipAccomplishmentService {

    private static Logger logger = LoggerFactory.getLogger(ScholarshipAccomplishmentServiceImpl.class);


    private ScholarshipAccomplishmentRepository scholarshipAccomplishmentRepository;
    private ScholarshipMapper scholarshipMapper;

    @Autowired
    public ScholarshipAccomplishmentServiceImpl(ScholarshipAccomplishmentRepository scholarshipAccomplishmentRepository,
                                                ScholarshipMapper scholarshipMapper) {
        this.scholarshipAccomplishmentRepository = scholarshipAccomplishmentRepository;
        this.scholarshipMapper = scholarshipMapper;
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishment() {
        return scholarshipAccomplishmentRepository.findAll()
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYear(Long year) {
        return scholarshipAccomplishmentRepository.findAllByYear(year)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYear(Long year, Month month) {
        return scholarshipAccomplishmentRepository.findAllByYearAndMonth(year, month)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYearAndType(Long year, Month month, ScholarshipType scholarshipType) {
        return scholarshipAccomplishmentRepository.findAllByYearAndMonthAndScholarshipType(year, month, scholarshipType)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public ScholarshipWrapper getAllScholarshipAccomplishment(Long year, Month month) {
        List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList =
                scholarshipAccomplishmentRepository.findAllByYearAndMonth(year, month)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
        if (scholarshipAccomplishmentDtoList.isEmpty()) {
            return new ScholarshipWrapper().initializeScholarshipWrapper(month, year);
        } else {
            ScholarshipWrapper scholarshipWrapper = new ScholarshipWrapper();
            scholarshipWrapper.setPesfaData(Lists.newArrayList());
            scholarshipWrapper.setRespData(Lists.newArrayList());
            scholarshipWrapper.setStepData(Lists.newArrayList());
            scholarshipWrapper.setTwspData(Lists.newArrayList());
            scholarshipWrapper.setUaqteaDiplomaData(Lists.newArrayList());
            scholarshipWrapper.setUaqteaSbData(Lists.newArrayList());
            scholarshipAccomplishmentDtoList.forEach(
                    scholarshipAccomplishmentDto -> {
                        switch (scholarshipAccomplishmentDto.getScholarshipType()) {
                            case TWSP:
                                scholarshipWrapper.getTwspData().add(scholarshipAccomplishmentDto);
                                break;
                            case STEP:
                                scholarshipWrapper.getStepData().add(scholarshipAccomplishmentDto);
                                break;
                            case PESFA:
                                scholarshipWrapper.getPesfaData().add(scholarshipAccomplishmentDto);
                                break;
                            case UAQTEA_SB:
                                scholarshipWrapper.getUaqteaSbData().add(scholarshipAccomplishmentDto);
                                break;
                            case UAQTEA_DIPLOMA:
                                scholarshipWrapper.getUaqteaDiplomaData().add(scholarshipAccomplishmentDto);
                                break;
                            case RESP:
                                scholarshipWrapper.getRespData().add(scholarshipAccomplishmentDto);
                                break;
                            default:
                                break;
                        }
                    }
            );
            return scholarshipWrapper;
        }
    }

    @Override
    @Transactional
    public void updateScholarshipAccomplishment(ScholarshipWrapper scholarshipWrapper) {
        createScholarshipAccomplishment(ScholarshipType.TWSP, scholarshipWrapper.getTwspData());
        createScholarshipAccomplishment(ScholarshipType.PESFA, scholarshipWrapper.getPesfaData());
        createScholarshipAccomplishment(ScholarshipType.STEP, scholarshipWrapper.getStepData());
        createScholarshipAccomplishment(ScholarshipType.RESP, scholarshipWrapper.getRespData());
        createScholarshipAccomplishment(ScholarshipType.UAQTEA_SB, scholarshipWrapper.getUaqteaSbData());
        createScholarshipAccomplishment(ScholarshipType.UAQTEA_DIPLOMA, scholarshipWrapper.getUaqteaDiplomaData());
    }

    @Override
    public void createScholarshipAccomplishment(ScholarshipType scholarshipType, List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList) {
        ScholarshipAccomplishmentDto scholarshipAccomplishmentSample = scholarshipAccomplishmentDtoList
                .stream()
                .findAny()
                .orElseThrow(EntityExistsException::new);
        ScholarshipAccomplishmentDto total = new ScholarshipAccomplishmentDto(OperatingUnitType.TOTAL, scholarshipType,
                scholarshipAccomplishmentSample.getMonth(), scholarshipAccomplishmentSample.getYear());

        scholarshipAccomplishmentDtoList.forEach(
                scholarshipAccomplishmentDto -> {
                    calculateTotal(total, scholarshipAccomplishmentDto);
                    saveScholarshipEntity(scholarshipAccomplishmentDto);
                }
        );

        saveTotal(total);
    }

    private void saveScholarshipEntity(ScholarshipAccomplishmentDto scholarshipAccomplishmentDto) {
        // check if update or create by id
        ScholarshipAccomplishment scholarshipAccomplishment;
        if (scholarshipAccomplishmentDto.getId() == null) {
            scholarshipAccomplishment = scholarshipMapper
                    .scholarshipAccomplishmentToEntity(scholarshipAccomplishmentDto);
        } else {
            scholarshipAccomplishment = scholarshipAccomplishmentRepository
                    .findById(scholarshipAccomplishmentDto.getId()).orElseThrow(EntityExistsException::new);
            scholarshipMapper.updatedScholarshipAccomplishmentToEntity(scholarshipAccomplishmentDto, scholarshipAccomplishment);
        }
        scholarshipAccomplishmentRepository.save(scholarshipAccomplishment);
    }

    private void calculateTotal(ScholarshipAccomplishmentDto total, ScholarshipAccomplishmentDto scholarshipAccomplishmentDto) {
        total.getQualificationMapDto().setAmount(
                total.getQualificationMapDto().getAmount() +
                        scholarshipAccomplishmentDto.getQualificationMapDto().getAmount());
        total.getQualificationMapDto().setSlots(
                total.getQualificationMapDto().getSlots() +
                        scholarshipAccomplishmentDto.getQualificationMapDto().getSlots());
        total.getFinancialAccomplishmentDto().setTotalObligation(
                total.getFinancialAccomplishmentDto().getTotalObligation() +
                        scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getTotalObligation());
        total.getFinancialAccomplishmentDto().setObligationRate(
                total.getFinancialAccomplishmentDto().getObligationRate() +
                        scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getObligationRate());
        total.getFinancialAccomplishmentDto().setTotalDisbursement(
                total.getFinancialAccomplishmentDto().getTotalDisbursement() +
                        scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getTotalDisbursement());
        total.getFinancialAccomplishmentDto().setDisbursementRate(
                total.getFinancialAccomplishmentDto().getDisbursementRate() +
                        scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getDisbursementRate());
        total.getPhysicalAccomplishmentDto().setEnrolled(
                total.getPhysicalAccomplishmentDto().getEnrolled() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEnrolled());
        total.getPhysicalAccomplishmentDto().setEnrolledUtilization(
                total.getPhysicalAccomplishmentDto().getEnrolledUtilization() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEnrolledUtilization());
        total.getPhysicalAccomplishmentDto().setGraduates(
                total.getPhysicalAccomplishmentDto().getGraduates() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduates());
        total.getPhysicalAccomplishmentDto().setGraduatesUtilization(
                total.getPhysicalAccomplishmentDto().getGraduatesUtilization() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduatesUtilization());
        total.getPhysicalAccomplishmentDto().setAssessed(
                total.getPhysicalAccomplishmentDto().getAssessed() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessed());
        total.getPhysicalAccomplishmentDto().setAssessedUtilization(
                total.getPhysicalAccomplishmentDto().getAssessedUtilization() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessedUtilization());
        total.getPhysicalAccomplishmentDto().setCertified(
                total.getPhysicalAccomplishmentDto().getCertified() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getCertified());
        total.getPhysicalAccomplishmentDto().setCertifiedUtilization(
                total.getPhysicalAccomplishmentDto().getCertifiedUtilization() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getCertifiedUtilization());
        total.getPhysicalAccomplishmentDto().setEmployed(
                total.getPhysicalAccomplishmentDto().getEmployed() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEmployed());
    }

    private void saveTotal(ScholarshipAccomplishmentDto total) {
        // calculate rate
        total.getFinancialAccomplishmentDto().setObligationRate(total.getFinancialAccomplishmentDto().getObligationRate()/6);
        total.getFinancialAccomplishmentDto().setDisbursementRate(total.getFinancialAccomplishmentDto().getDisbursementRate()/6);
        total.getPhysicalAccomplishmentDto().setEnrolledUtilization(total.getPhysicalAccomplishmentDto().getEnrolledUtilization()/6);
        total.getPhysicalAccomplishmentDto().setGraduatesUtilization(total.getPhysicalAccomplishmentDto().getGraduatesUtilization()/6);
        total.getPhysicalAccomplishmentDto().setAssessedUtilization(total.getPhysicalAccomplishmentDto().getAssessedUtilization()/6);
        total.getPhysicalAccomplishmentDto().setCertifiedUtilization(total.getPhysicalAccomplishmentDto().getCertifiedUtilization()/6);


        // fetch old total if existing then update, if not then create new entity
        ScholarshipAccomplishment oldTotal = scholarshipAccomplishmentRepository
                .findByYearAndMonthAndScholarshipTypeAndOperatingUnitType(total.getYear(), total.getMonth(),
                        total.getScholarshipType(), total.getOperatingUnitType());

        scholarshipAccomplishmentRepository.save(Optional.ofNullable(oldTotal)
                .map(scholarshipAccomplishmentDto -> scholarshipMapper.updatedScholarshipAccomplishmentToEntity(total,oldTotal))
                .orElse(scholarshipMapper.scholarshipAccomplishmentToEntity(total)));
    }
}
