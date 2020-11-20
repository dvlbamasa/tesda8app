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
import com.tesda8.region8.util.enums.OperatingUnitPOType;
import com.tesda8.region8.util.enums.PapGroupType;
import com.tesda8.region8.util.service.ReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PapDataServiceImpl implements PapDataService {

    private static Logger logger = LoggerFactory.getLogger(PapDataServiceImpl.class);

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
    public PapDataWrapper getAllPapDataWrapperByFilter(String measureFilter, String papNameFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.name.toLowerCase().trim()
                .containsIgnoreCase(Optional.of(papNameFilter.trim()).orElse("")));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.papData.isDeleted.eq(false));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.isDeleted.eq(false));

        booleanBuilder.and(QSuccessIndicatorData.successIndicatorData.measures.containsIgnoreCase(
                Optional.of(measureFilter.trim()).orElse("")));

        Predicate predicate = booleanBuilder.getValue();

        List<SuccessIndicatorData> successIndicatorDataList =
                (List<SuccessIndicatorData>) successIndicatorDataRepository.findAll(predicate);

        List<SuccessIndicatorDataDto> successIndicatorDataDtoList = successIndicatorDataList.stream()
                .map(this::sortOperatingUnitData)
                .map(successIndicatorData -> planningMapper.successIndicatorToDto(successIndicatorData))
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

    @Override
    public List<PapDataDto> getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType papGroupType, String measureFilter, String papName) {
        List<PapData> papDataList = papDataRepository.findAllByPapGroupType(papGroupType);
        papDataList.forEach(
                papData -> {
                    papData.setSuccessIndicatorDataList(
                            papData.getSuccessIndicatorDataList()
                            .stream()
                            .filter(successIndicatorData -> !successIndicatorData.getIsDeleted())
                            .filter(successIndicatorData -> successIndicatorData.getMeasures()
                                    .toLowerCase()
                                    .contains(Optional.ofNullable(measureFilter).orElse("")
                                            .toLowerCase().trim()))
                            .map(this::sortOperatingUnitData)
                            .collect(Collectors.toList())
                    );
                }
        );
        //include only papdata with successindicators
        return papDataList
                .stream()
                .filter(papData -> !papData.getIsDeleted())
                .filter(papData -> papData.getSuccessIndicatorDataList().size() > 0)
                .filter(papData -> papData.getName()
                        .toLowerCase()
                        .contains(Optional.ofNullable(papName).orElse("")
                                .toLowerCase().trim()))
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }

    @Override
    public SuccessIndicatorDataDto getSuccessIndicatorData(Long id) {
        SuccessIndicatorData successIndicatorData = successIndicatorDataRepository.getOne(id);
        sortOperatingUnitData(successIndicatorData);
        return planningMapper.successIndicatorToDto(successIndicatorData);
    }

    @Override
    public void updatePapData(List<SuccessIndicatorDataDto> successIndicatorDataDtoList) {
        successIndicatorDataDtoList.forEach(
                successIndicatorDataDto -> {
                    // total initialize
                    OperatingUnitDataDto total = new OperatingUnitDataDto();
                    SuccessIndicatorData successIndicatorData = successIndicatorDataRepository.getOne(successIndicatorDataDto.getId());
                    total.setTarget(successIndicatorData.getTarget());
                    total.setOutput(0);

                    OperatingUnitData oldTotal = successIndicatorData.getOperatingUnitDataList()
                            .stream()
                            .filter(operatingUnitData -> operatingUnitData.getOperatingUnitType().equals(OperatingUnitPOType.TOTAL))
                            .findAny().get();

                    successIndicatorDataDto.getOperatingUnitDataList().forEach(
                            operatingUnitDataDto -> {
                                OperatingUnitData operatingUnitData1 =
                                        operatingUnitDataRepository.getOne(operatingUnitDataDto.getId());
                                setValues(operatingUnitData1, operatingUnitDataDto);
                                operatingUnitDataRepository.save(operatingUnitData1);
                                calculateTotal(total, operatingUnitDataDto);
                            }
                    );

                    if (!successIndicatorData.getIsAccumulated()) {
                        total.setOutput(total.getOutput()/7);
                    }
                    total.setRate(ReportUtil.calculateRate(total.getTarget(), total.getOutput()));
                    setValues(oldTotal, total);
                    operatingUnitDataRepository.save(oldTotal);
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
                    setValues(successIndicatorData, successIndicatorDataDto);
                    successIndicatorDataRepository.save(successIndicatorData);
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

        //total initialize
        OperatingUnitData oldTotal = new OperatingUnitData();
        oldTotal.setTarget(Long.valueOf(successIndicatorData.getTarget()));
        oldTotal.setOutput(0L);
        oldTotal.setOperatingUnitType(OperatingUnitPOType.TOTAL);
        oldTotal.setSuccessIndicatorData(successIndicatorData);

        List<OperatingUnitData> operatingUnitDataList = Lists.newArrayList();

        successIndicatorDataDto.getOperatingUnitDataList().forEach(
                operatingUnitDataDto -> {
                    operatingUnitDataDto.setRate(ReportUtil.calculateRate(operatingUnitDataDto.getTarget(), operatingUnitDataDto.getOutput()));
                    OperatingUnitData operatingUnitData = planningMapper.operatingUnitDataToEntity(operatingUnitDataDto);
                    operatingUnitData.setSuccessIndicatorData(successIndicatorData);
                    oldTotal.setOutput(operatingUnitDataDto.getOutput() + oldTotal.getOutput());
                    operatingUnitDataList.add(operatingUnitData);
                }
        );
        if (!successIndicatorData.getIsAccumulated()) {
            oldTotal.setOutput(oldTotal.getOutput()/7);
        }
        oldTotal.setRate(ReportUtil.calculateRate(oldTotal.getTarget(), oldTotal.getOutput()));
        operatingUnitDataList.add(oldTotal);
        successIndicatorData.setOperatingUnitDataList(operatingUnitDataList);
        successIndicatorDataRepository.save(successIndicatorData);
    }

    @Override
    @Transactional
    public void createPapData(PapDataDto papDataDto) {
        PapData papData = planningMapper.papDataToEntity(papDataDto);
        papData.setSuccessIndicatorDataList(Lists.newArrayList());
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

    private void setValues(SuccessIndicatorData successIndicatorData, SuccessIndicatorDataDto successIndicatorDataDto) {
        successIndicatorData.setIsAccumulated(successIndicatorDataDto.getIsAccumulated());
        successIndicatorData.setIsPercentage(successIndicatorDataDto.getIsPercentage());
        successIndicatorData.setIsDeleted(successIndicatorDataDto.getIsDeleted());
        successIndicatorData.setMeasures(successIndicatorDataDto.getMeasures());
        successIndicatorData.setTarget(successIndicatorDataDto.getTarget());
        successIndicatorData.setUpdatedDate(LocalDateTime.now());
    }

    private void setValues(OperatingUnitData operatingUnitData, OperatingUnitDataDto operatingUnitDataDto) {
        operatingUnitData.setTarget(operatingUnitDataDto.getTarget());
        operatingUnitData.setOutput(operatingUnitDataDto.getOutput());
        operatingUnitData.setRate(ReportUtil.calculateRate(operatingUnitDataDto.getTarget(), operatingUnitDataDto.getOutput()));
        operatingUnitData.setUpdatedDate(LocalDateTime.now());
    }

    private void calculateTotal(OperatingUnitDataDto total, OperatingUnitDataDto operatingUnitDataDto) {
        total.setOutput(total.getOutput() + operatingUnitDataDto.getOutput());
    }
}
