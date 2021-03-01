package com.tesda8.region8.planning.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.planning.model.dto.OperatingUnitDataDto;
import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.planning.model.entities.QSuccessIndicatorData;
import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import com.tesda8.region8.planning.model.mapper.PlanningMapper;
import com.tesda8.region8.planning.model.wrapper.PapDataWrapper;
import com.tesda8.region8.planning.repository.OperatingUnitDataRepository;
import com.tesda8.region8.planning.repository.PapDataRepository;
import com.tesda8.region8.planning.repository.SuccessIndicatorDataRepository;
import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitPOType;
import com.tesda8.region8.util.enums.PapGroupType;
import com.tesda8.region8.util.enums.SuccessIndicatorType;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.util.service.ReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PapDataServiceImpl implements PapDataService {

    private static Logger logger = LoggerFactory.getLogger(PapDataServiceImpl.class);
    private static final String PLANNING_ROLE = "PLANNING";
    private static final String ADMIN_ROLE = "ADMIN";


    private PapDataRepository papDataRepository;
    private PlanningMapper planningMapper;
    private OperatingUnitDataRepository operatingUnitDataRepository;
    private SuccessIndicatorDataRepository successIndicatorDataRepository;

    @Autowired
    public PapDataServiceImpl(PapDataRepository papDataRepository, PlanningMapper planningMapper,
                              OperatingUnitDataRepository operatingUnitDataRepository,
                              SuccessIndicatorDataRepository successIndicatorDataRepository) {
        this.papDataRepository = papDataRepository;
        this.planningMapper = planningMapper;
        this.operatingUnitDataRepository = operatingUnitDataRepository;
        this.successIndicatorDataRepository = successIndicatorDataRepository;
    }

    @Override
    public List<PapDataDto> getAllPapData() {
        List<PapData> papDataList = papDataRepository.findAll();
        papDataList.forEach(
                papData -> {
                    papData.getSuccessIndicatorDataList().forEach(
                            successIndicatorData -> {
                                successIndicatorData.getOperatingUnitDataList()
                                        .sort(Comparator.comparing(OperatingUnitData::getId));
                            }
                    );
                }
        );
        return papDataList
                .stream()
                .filter(papData -> !papData.getIsDeleted())
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }

    @Override
    public List<PapDataDto> getAllPapDataByYear(Long year) {
        List<PapData> papDataList = papDataRepository.findAllByYear(year);
        return papDataList
                .stream()
                .filter(papData -> !papData.getIsDeleted())
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }

    @Override
    public List<PapDataDto> getAllPapDataByPapGroupType(PapGroupType papGroupType) {
        List<PapData> papDataList = papDataRepository.findAllByPapGroupType(papGroupType);
        papDataList.forEach(
                papData -> {
                    List<SuccessIndicatorData> collect = papData.getSuccessIndicatorDataList().stream()
                            .filter(successIndicatorData -> !successIndicatorData.getIsDeleted())
                            .map(this::sortOperatingUnitData)
                            .collect(Collectors.toList());
                    papData.setSuccessIndicatorDataList(collect);
                }
        );
        return papDataList
                .stream()
                .filter(papData -> !papData.getIsDeleted())
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }

    @Override
    public PapDataWrapper getAllPapDataWrapperByFilter(String measureFilter, String papNameFilter, Long year, Month month, String roleName, String pageType) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        SuccessIndicatorType successIndicatorTypeFilter = pageType.equals("PO") ?
                SuccessIndicatorType.RO_PO : SuccessIndicatorType.TTI;

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.name.toLowerCase().trim()
                .containsIgnoreCase(Optional.of(papNameFilter.trim()).orElse("")));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.year.eq(year));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.isDeleted.eq(false));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.isDeleted.eq(false));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.measures.containsIgnoreCase(
                Optional.of(measureFilter.trim()).orElse("")));

        BooleanBuilder successIndicatorTypePredicate = new BooleanBuilder();
        successIndicatorTypePredicate.or(QSuccessIndicatorData.successIndicatorData.successIndicatorType.eq(SuccessIndicatorType.RO_PO_TTI));
        successIndicatorTypePredicate.or(QSuccessIndicatorData.successIndicatorData.successIndicatorType.eq(successIndicatorTypeFilter));

        booleanBuilder.and(successIndicatorTypePredicate);

        Predicate predicate = booleanBuilder.getValue();

        List<SuccessIndicatorData> successIndicatorDataList =
                (List<SuccessIndicatorData>) successIndicatorDataRepository.findAll(predicate);

        List<SuccessIndicatorDataDto> successIndicatorDataDtoList = successIndicatorDataList.stream()
                .map(this::sortOperatingUnitData)
                .map(successIndicatorData -> planningMapper.successIndicatorToDto(successIndicatorData))
                .map(successIndicatorDataDto -> filterOperatingUnitData(successIndicatorDataDto, roleName, month))
                .collect(Collectors.toList());

        PapDataWrapper papDataWrapper = new PapDataWrapper();
        papDataWrapper.setTesdppData(Lists.newArrayList());
        papDataWrapper.setTesdrpData(Lists.newArrayList());
        papDataWrapper.setTesdpData(Lists.newArrayList());
        papDataWrapper.setStoData(Lists.newArrayList());
        papDataWrapper.setGassData(Lists.newArrayList());

        successIndicatorDataDtoList.forEach(
                successIndicatorDataDto -> {
                    switch (successIndicatorDataDto.getPapGroupType()) {
                        case TESDPP:
                            papDataWrapper.getTesdppData().add(successIndicatorDataDto);
                            break;
                        case TESDRP:
                            papDataWrapper.getTesdrpData().add(successIndicatorDataDto);
                            break;
                        case TESDP:
                            papDataWrapper.getTesdpData().add(successIndicatorDataDto);
                            break;
                        case GASS:
                            papDataWrapper.getGassData().add(successIndicatorDataDto);
                            break;
                        case STO:
                            papDataWrapper.getStoData().add(successIndicatorDataDto);
                            break;
                        default:
                            break;
                    }
                }
        );
        return papDataWrapper;
    }

    private SuccessIndicatorData sortOperatingUnitData(SuccessIndicatorData successIndicatorData) {
        successIndicatorData.getOperatingUnitDataList().sort(Comparator.comparing(OperatingUnitData::getId));
        return successIndicatorData;
    }

    private SuccessIndicatorDataDto filterOperatingUnitData(SuccessIndicatorDataDto successIndicatorDataDto, String role, Month month) {
        String successIndicatorType = ApplicationUtil.getSuccessIndicatorType(role);

        if (role.equals(PLANNING_ROLE) || role.equals(ADMIN_ROLE)) {
            successIndicatorDataDto.setTtiDataList(
                    successIndicatorDataDto.getOperatingUnitDataList().stream()
                            .filter(operatingUnitDataDto -> operatingUnitDataDto.getOperatingUnitType().successIndicatorType.equals("TTI"))
                            .filter(operatingUnitDataDto -> operatingUnitDataDto.getMonth().equals(month))
                            .collect(Collectors.toList())
            );
            successIndicatorDataDto.setOperatingUnitDataList(
                    successIndicatorDataDto.getOperatingUnitDataList().stream()
                            .filter(operatingUnitDataDto -> operatingUnitDataDto.getOperatingUnitType().successIndicatorType.equals("PO"))
                            .filter(operatingUnitDataDto -> operatingUnitDataDto.getMonth().equals(month))
                            .collect(Collectors.toList())
            );

        } else {
            if (successIndicatorType.equals("PO")) {
                successIndicatorDataDto.setOperatingUnitDataList(
                        successIndicatorDataDto.getOperatingUnitDataList().stream()
                                .filter(operatingUnitDataDto -> operatingUnitDataDto.getOperatingUnitType().equals(OperatingUnitPOType.valueOf(role)))
                                .filter(operatingUnitDataDto -> operatingUnitDataDto.getMonth().equals(month))
                                .collect(Collectors.toList())
                );
            } else {
                successIndicatorDataDto.setTtiDataList(
                        successIndicatorDataDto.getOperatingUnitDataList().stream()
                                .filter(operatingUnitDataDto -> operatingUnitDataDto.getOperatingUnitType().equals(OperatingUnitPOType.valueOf(role)))
                                .filter(operatingUnitDataDto -> operatingUnitDataDto.getMonth().equals(month))
                                .collect(Collectors.toList())
                );
            }

        }

        return successIndicatorDataDto;
    }

    private SuccessIndicatorDataDto filterOperatingUnitDataDtoForGraph(SuccessIndicatorDataDto successIndicatorDataDto, String pageType, Month month) {
        successIndicatorDataDto.setOperatingUnitDataList(
                successIndicatorDataDto.getOperatingUnitDataList().stream()
                        .filter(operatingUnitDataDto -> operatingUnitDataDto.getOperatingUnitType().successIndicatorType.equals(pageType))
                        .filter(operatingUnitDataDto -> operatingUnitDataDto.getMonth().equals(month))
                        .collect(Collectors.toList())
        );
        return successIndicatorDataDto;
    }

    @Override
    public List<SuccessIndicatorDataDto> getAllSuccessIndicatorsByFilter(PapGroupType papGroupType, String measureFilter,
                                                                         String papName, Long year, Month month, String pageType) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.name.toLowerCase().trim()
                .containsIgnoreCase(Optional.of(papName.trim()).orElse("")));

        SuccessIndicatorType successIndicatorTypeFilter = pageType.equals("PO") ?
                SuccessIndicatorType.RO_PO : SuccessIndicatorType.TTI;

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.papGroupType.eq(papGroupType));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.year.eq(year));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.isDeleted.eq(false));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.isDeleted.eq(false));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.measures.containsIgnoreCase(
                Optional.of(measureFilter.trim()).orElse("")));

        BooleanBuilder successIndicatorTypePredicate = new BooleanBuilder();
        successIndicatorTypePredicate.or(QSuccessIndicatorData.successIndicatorData.successIndicatorType.eq(SuccessIndicatorType.RO_PO_TTI));
        successIndicatorTypePredicate.or(QSuccessIndicatorData.successIndicatorData.successIndicatorType.eq(successIndicatorTypeFilter));

        booleanBuilder.and(successIndicatorTypePredicate);

        Predicate predicate = booleanBuilder.getValue();

        List<SuccessIndicatorData> successIndicatorDataList =
                (List<SuccessIndicatorData>) successIndicatorDataRepository.findAll(predicate);

        return successIndicatorDataList.stream()
                .map(this::sortOperatingUnitData)
                .map(successIndicatorData -> planningMapper.successIndicatorToDto(successIndicatorData))
                .map(successIndicatorDataDto -> filterOperatingUnitDataDtoForGraph(successIndicatorDataDto, pageType, month))
                .collect(Collectors.toList());
    }

    @Override
    public SuccessIndicatorDataDto getSuccessIndicatorData(Long id, String pageType) {
        SuccessIndicatorData successIndicatorData = successIndicatorDataRepository.getOne(id);
        sortOperatingUnitData(successIndicatorData);
        return planningMapper.successIndicatorToDto(successIndicatorData);
    }

    @Override
    public void updatePapData(List<SuccessIndicatorDataDto> successIndicatorDataDtoList, String updateType, Month month) {
        successIndicatorDataDtoList.forEach(
                successIndicatorDataDto -> {

                    if (updateType.equals("PO")) {
                        // total initialize
                        SuccessIndicatorData successIndicatorData = successIndicatorDataRepository.getOne(successIndicatorDataDto.getId());

                        OperatingUnitDataDto total = new OperatingUnitDataDto();
                        total.setTarget(successIndicatorData.getTarget());
                        total.setOutput(0);
                        total.setMonth(month);

                        OperatingUnitData oldTotal = successIndicatorData.getOperatingUnitDataList()
                                .stream()
                                .filter(operatingUnitData -> operatingUnitData.getOperatingUnitType().equals(OperatingUnitPOType.TOTAL))
                                .filter(operatingUnitData -> operatingUnitData.getMonth().equals(month))
                                .findAny().get();

                        if (successIndicatorDataDto.getOperatingUnitDataList().size() == 1) {
                            /*
                            Checks if the OPCR was updated by a PO or a Planning Admin
                            */
                            OperatingUnitDataDto operatingUnitDataDto = successIndicatorDataDto.getOperatingUnitDataList().stream()
                                    .filter(operatingUnitDataDto1 -> operatingUnitDataDto1.getId() != 0)
                                    .findFirst().orElseThrow(EntityNotFoundException::new);

                            OperatingUnitData operatingUnitData = operatingUnitDataRepository.getOne(operatingUnitDataDto.getId());
                            planningMapper.updatedOperatingUnitData(operatingUnitDataDto, operatingUnitData);
                            operatingUnitData.setRate(ReportUtil.calculateRate(operatingUnitData.getTarget(), operatingUnitData.getOutput()));
                            operatingUnitDataRepository.save(operatingUnitData);

                            SuccessIndicatorData successIndicator = successIndicatorDataRepository.getOne(successIndicatorDataDto.getId());

                            successIndicator.getOperatingUnitDataList().forEach(
                                    operatingUnitData1 -> {
                                        if (!operatingUnitData1.getOperatingUnitType().equals(OperatingUnitPOType.TOTAL) &&
                                            operatingUnitData1.getOperatingUnitType().successIndicatorType.equals("PO") &&
                                            operatingUnitData1.getMonth().equals(month)) {
                                            calculateTotal(total, operatingUnitData1);
                                        }
                                    }
                            );
                        } else {
                            successIndicatorDataDto.getOperatingUnitDataList().forEach(
                                    operatingUnitDataDtoInstance -> {
                                        OperatingUnitData operatingUnitData1 =
                                                operatingUnitDataRepository.getOne(operatingUnitDataDtoInstance.getId());
                                        planningMapper.updatedOperatingUnitData(operatingUnitDataDtoInstance, operatingUnitData1);
                                        operatingUnitData1.setRate(ReportUtil.calculateRate(operatingUnitData1.getTarget(), operatingUnitData1.getOutput()));
                                        operatingUnitDataRepository.save(operatingUnitData1);
                                        calculateTotal(total, operatingUnitDataDtoInstance);
                                    }
                            );
                        }

                        if (!successIndicatorData.getIsAccumulated()) {
                            total.setOutput(total.getOutput()/7);
                        }
                        total.setRate(ReportUtil.calculateRate(total.getTarget(), total.getOutput()));
                        planningMapper.updatedOperatingUnitData(total, oldTotal);
                        oldTotal.setRate(ReportUtil.calculateRate(oldTotal.getTarget(), oldTotal.getOutput()));
                        operatingUnitDataRepository.save(oldTotal);

                    } else {
                        successIndicatorDataDto.getTtiDataList().forEach(
                                operatingUnitDataDtoInstance -> {
                                    OperatingUnitData operatingUnitData1 =
                                            operatingUnitDataRepository.getOne(operatingUnitDataDtoInstance.getId());
                                    planningMapper.updatedOperatingUnitData(operatingUnitDataDtoInstance, operatingUnitData1);
                                    operatingUnitData1.setRate(ReportUtil.calculateRate(operatingUnitData1.getTarget(), operatingUnitData1.getOutput()));
                                    operatingUnitDataRepository.save(operatingUnitData1);
                                }
                        );

                    }
                 }
        );
    }

    @Override
    @Transactional
    public void updateSuccessIndicators(List<SuccessIndicatorDataDto> successIndicatorDataDtoList) {
        successIndicatorDataDtoList.forEach(
                successIndicatorDataDto -> {
                    SuccessIndicatorData successIndicatorData =
                            successIndicatorDataRepository.getOne(successIndicatorDataDto.getId());
                    successIndicatorDataRepository.save(planningMapper
                            .updatedSuccessIndicatorData(successIndicatorDataDto, successIndicatorData));
                    OperatingUnitData oldTotal = successIndicatorData.getOperatingUnitDataList()
                            .stream()
                            .filter(operatingUnitData -> operatingUnitData.getOperatingUnitType().equals(OperatingUnitPOType.TOTAL))
                            .findAny().get();
                    oldTotal.setTarget((long) successIndicatorDataDto.getTarget());
                    oldTotal.setRate(ReportUtil.calculateRate(oldTotal.getTarget(), oldTotal.getOutput()));
                    operatingUnitDataRepository.save(oldTotal);
                }
        );
    }

    @Override
    @Transactional
    public void createSuccessIndicator(SuccessIndicatorDataDto successIndicatorDataDto) {
        PapData papData = papDataRepository.getOne(successIndicatorDataDto.getPapDataId());
        SuccessIndicatorData successIndicatorData = planningMapper.successIndicatorToEntity(successIndicatorDataDto);
        successIndicatorData.setPapData(papData);
        successIndicatorData.setOperatingUnitDataList(null);
        successIndicatorData.setIsDeleted(false);

        List<OperatingUnitData> operatingUnitDataList = Lists.newArrayList();

        if (!successIndicatorDataDto.getSuccessIndicatorType().equals(SuccessIndicatorType.RO_PO)) {
            successIndicatorDataDto.getOperatingUnitDataList().forEach(
                    operatingUnitDataDto -> {
                        if (operatingUnitDataDto.getOperatingUnitType().successIndicatorType.equals("TTI")) {
                            double rate = ReportUtil.calculateRate(operatingUnitDataDto.getTarget(), operatingUnitDataDto.getOutput());
                            operatingUnitDataDto.setRate(rate);
                            Arrays.asList(Month.values()).forEach(
                                    month -> {
                                        OperatingUnitData operatingUnitData = planningMapper.operatingUnitDataToEntity(operatingUnitDataDto);
                                        operatingUnitData.setSuccessIndicatorData(successIndicatorData);
                                        operatingUnitData.setMonth(month);
                                        operatingUnitDataList.add(operatingUnitData);
                                    }
                            );

                        }
                    }
            );
        }
        if (!successIndicatorDataDto.getSuccessIndicatorType().equals(SuccessIndicatorType.TTI)) {
            //total initialize
            Arrays.asList(Month.values()).forEach(
                    month -> {
                        OperatingUnitData total = new OperatingUnitData();
                        total.setTarget(Long.valueOf(successIndicatorData.getTarget()));
                        total.setOutput(0L);
                        total.setOperatingUnitType(OperatingUnitPOType.TOTAL);
                        total.setSuccessIndicatorData(successIndicatorData);
                        total.setMonth(month);

                        successIndicatorDataDto.getOperatingUnitDataList().forEach(
                                operatingUnitDataDto -> {
                                    if (operatingUnitDataDto.getOperatingUnitType().successIndicatorType.equals("PO")) {
                                        operatingUnitDataDto.setRate(ReportUtil.calculateRate(operatingUnitDataDto.getTarget(), operatingUnitDataDto.getOutput()));
                                        OperatingUnitData operatingUnitData = planningMapper.operatingUnitDataToEntity(operatingUnitDataDto);
                                        operatingUnitData.setSuccessIndicatorData(successIndicatorData);
                                        operatingUnitData.setMonth(month);
                                        total.setOutput(operatingUnitDataDto.getOutput() + total.getOutput());
                                        operatingUnitDataList.add(operatingUnitData);
                                    }
                                }
                        );
                        if (!successIndicatorData.getIsAccumulated()) {
                            total.setOutput(total.getOutput()/7);
                        }
                        total.setRate(ReportUtil.calculateRate(total.getTarget(), total.getOutput()));
                        operatingUnitDataList.add(total);
            });

        }
        successIndicatorData.setOperatingUnitDataList(operatingUnitDataList);
        successIndicatorDataRepository.save(successIndicatorData);
    }

    @Override
    @Transactional
    public void createPapData(PapDataDto papDataDto, Long year) {
        PapData papData = planningMapper.papDataToEntity(papDataDto);
        papData.setSuccessIndicatorDataList(Lists.newArrayList());
        papData.setYear(year);
        papData.setIsDeleted(false);
        papDataRepository.save(papData);
    }

    @Override
    @Transactional
    public void deletePapData(PapDataDto papDataDto) {
        PapData papData = papDataRepository.getOne(papDataDto.getId());
        papData.setIsDeleted(true);
        papDataRepository.save(papData);
    }

    private void calculateTotal(OperatingUnitDataDto total, OperatingUnitDataDto operatingUnitDataDto) {
        total.setOutput(total.getOutput() + operatingUnitDataDto.getOutput());
    }

    private void calculateTotal(OperatingUnitDataDto total, OperatingUnitData operatingUnitData) {
        total.setOutput(total.getOutput() + operatingUnitData.getOutput());

    }
}
