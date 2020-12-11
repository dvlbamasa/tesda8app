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
import com.tesda8.region8.util.service.ReportUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarshipAccomplishmentServiceImpl implements ScholarshipAccomplishmentService {

    private static final Logger logger = LoggerFactory.getLogger(ScholarshipAccomplishmentServiceImpl.class);

    private final ScholarshipAccomplishmentRepository scholarshipAccomplishmentRepository;
    private final ScholarshipMapper scholarshipMapper;

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishment() {
        return scholarshipAccomplishmentRepository.findAll()
                .stream()
                .map(scholarshipMapper::scholarshipAccomplishmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYear(Long year) {
        return scholarshipAccomplishmentRepository.findAllByYear(year)
                .stream()
                .map(scholarshipMapper::scholarshipAccomplishmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYear(Long year, Month month) {
        return scholarshipAccomplishmentRepository.findAllByYearAndMonthOrderById(year, month)
                .stream()
                .map(scholarshipMapper::scholarshipAccomplishmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYearAndType(Long year, Month month, ScholarshipType scholarshipType) {
        return scholarshipAccomplishmentRepository.findAllByYearAndMonthAndScholarshipTypeOrderById(year, month, scholarshipType)
                .stream()
                .map(scholarshipMapper::scholarshipAccomplishmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScholarshipWrapper getAllScholarshipAccomplishment(Long year, Month month) {
        List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList =
                scholarshipAccomplishmentRepository.findAllByYearAndMonthOrderById(year, month)
                .stream()
                .map(scholarshipMapper::scholarshipAccomplishmentToDto)
                .collect(Collectors.toList());
        if (scholarshipAccomplishmentDtoList.isEmpty()) {
            return new ScholarshipWrapper().initializeScholarshipWrapper(month, year);
        } else {
            ScholarshipWrapper scholarshipWrapper = new ScholarshipWrapper();
            scholarshipWrapper.setMonth(month);
            scholarshipWrapper.setYear(year);
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
        logger.info("month: {}, year: {}", scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
        createScholarshipAccomplishment(ScholarshipType.TWSP, scholarshipWrapper.getTwspData(), scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
        createScholarshipAccomplishment(ScholarshipType.PESFA, scholarshipWrapper.getPesfaData(), scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
        createScholarshipAccomplishment(ScholarshipType.STEP, scholarshipWrapper.getStepData(), scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
        createScholarshipAccomplishment(ScholarshipType.RESP, scholarshipWrapper.getRespData(), scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
        createScholarshipAccomplishment(ScholarshipType.UAQTEA_SB, scholarshipWrapper.getUaqteaSbData(), scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
        createScholarshipAccomplishment(ScholarshipType.UAQTEA_DIPLOMA, scholarshipWrapper.getUaqteaDiplomaData(), scholarshipWrapper.getMonth(), scholarshipWrapper.getYear());
    }

    @Override
    public void createScholarshipAccomplishment(ScholarshipType scholarshipType, List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList,
                                                Month month, Long year) {
        ScholarshipAccomplishmentDto total = new ScholarshipAccomplishmentDto(OperatingUnitType.TOTAL, scholarshipType,
                month, year);

        scholarshipAccomplishmentDtoList.forEach(
                scholarshipAccomplishmentDto -> {
                    calculateValues(scholarshipAccomplishmentDto);
                    calculateTotal(total, scholarshipAccomplishmentDto);
                    saveScholarshipEntity(scholarshipAccomplishmentDto);
                }
        );
        calculateValues(total);
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
                total.getQualificationMapDto().getAmount().add(scholarshipAccomplishmentDto.getQualificationMapDto().getAmount()));
        total.getQualificationMapDto().setSlots(
                total.getQualificationMapDto().getSlots() +
                        scholarshipAccomplishmentDto.getQualificationMapDto().getSlots());
        total.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().setAmountFundsDownloadable(
                total.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAmountFundsDownloadable()
                .add(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAmountFundsDownloadable()));
        total.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().setAdaAmount(
                total.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAdaAmount()
                .add(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAdaAmount()));
        total.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setTotalObligation(
                total.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation()
                        .add(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation()));
        total.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setTotalDisbursement(
                total.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalDisbursement()
                        .add(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalDisbursement()));
        total.getPhysicalAccomplishmentDto().setEnrolled(
                total.getPhysicalAccomplishmentDto().getEnrolled() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEnrolled());
        total.getPhysicalAccomplishmentDto().setGraduates(
                total.getPhysicalAccomplishmentDto().getGraduates() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduates());
        total.getPhysicalAccomplishmentDto().setAssessed(
                total.getPhysicalAccomplishmentDto().getAssessed() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessed());
        total.getPhysicalAccomplishmentDto().setCertified(
                total.getPhysicalAccomplishmentDto().getCertified() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getCertified());
        total.getPhysicalAccomplishmentDto().setEmployed(
                total.getPhysicalAccomplishmentDto().getEmployed() +
                        scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEmployed());
    }

    private void calculateValues(ScholarshipAccomplishmentDto scholarshipAccomplishmentDto) {
        BigDecimal amountDownloaded = scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAdaAmount();

        // Balance = (Total Amount from TAQM - Amount/Funds Downloaded)
        scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().setBalance(
                scholarshipAccomplishmentDto.getQualificationMapDto().getAmount()
                        .subtract(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAdaAmount()));


        /* Obligation = (Obligation - Amount/Funds Downloaded)
        scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setTotalObligation(
                scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation()
                        .subtract(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAdaAmount()));
        */

        // % Obligation = (Obligation / Amount/Funds Downloaded)
        if (!amountDownloaded.equals(BigDecimal.ZERO) && !amountDownloaded.equals(new BigDecimal("0.00"))) {
            scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setObligationRate(
                    ReportUtil.calculateRate(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation(),
                            scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getRoFinancialAccomplishmentDto().getAdaAmount()));
        } else {
            scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setObligationRate(BigDecimal.ZERO);
        }

        // % Disbursement = (Disbursement / Obligation)
        if (!scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation().equals(BigDecimal.ZERO) &&
                !scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation().equals(new BigDecimal("0.00"))) {
            scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setDisbursementRate(
                    ReportUtil.calculateRate(scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalDisbursement(),
                            scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().getTotalObligation()));
        } else {
            scholarshipAccomplishmentDto.getFinancialAccomplishmentDto().getPoFinancialAccomplishmentDto().setDisbursementRate(BigDecimal.ZERO);
        }

        // % Utilization = (Enrolled / Slots)
        if (scholarshipAccomplishmentDto.getQualificationMapDto().getSlots() != 0L) {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setEnrolledUtilization(
                    ReportUtil.calculateRate(scholarshipAccomplishmentDto.getQualificationMapDto().getSlots(), scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEnrolled()));
        } else {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setEnrolledUtilization(0.00);
        }

        // % Completion = (Graduates / Enrolled)
        if (scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEnrolled() != 0L) {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setGraduatesUtilization(
                    ReportUtil.calculateRate(scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getEnrolled(), scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduates()));
        } else {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setGraduatesUtilization(0.00);
        }

        // % Assessment = (Assessed / Graduates)
        if (scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduates() != 0L) {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setAssessedUtilization(
                    ReportUtil.calculateRate(scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getGraduates(), scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessed()));
        } else {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setAssessedUtilization(0.00);
        }

        // % Certified = (Certified / Assessed)
        if (scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessed() != 0L) {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setCertifiedUtilization(
                    ReportUtil.calculateRate(scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getAssessed(), scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().getCertified()));
        } else {
            scholarshipAccomplishmentDto.getPhysicalAccomplishmentDto().setCertifiedUtilization(0.00);
        }
    }

    private void saveTotal(ScholarshipAccomplishmentDto total) {
        // fetch old total if existing then update, if not then create new entity
        ScholarshipAccomplishment oldTotal = scholarshipAccomplishmentRepository
                .findByYearAndMonthAndScholarshipTypeAndOperatingUnitTypeOrderById(total.getYear(), total.getMonth(),
                        total.getScholarshipType(), total.getOperatingUnitType());
        scholarshipAccomplishmentRepository.save(Optional.ofNullable(oldTotal)
                .map(scholarshipAccomplishmentDto -> scholarshipMapper.updatedScholarshipAccomplishmentToEntity(total,oldTotal))
                .orElse(scholarshipMapper.scholarshipAccomplishmentToEntity(total)));
    }
}
