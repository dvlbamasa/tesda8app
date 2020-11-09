package com.tesda8.region8.planning.service.impl;

import com.tesda8.region8.planning.model.dto.OperatingUnitDataDto;
import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import com.tesda8.region8.planning.model.mapper.PlanningMapper;
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
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }

    private SuccessIndicatorData sortOperatingUnitData(SuccessIndicatorData successIndicatorData) {
        successIndicatorData.getOperatingUnitDataList().sort(Comparator.comparing(OperatingUnitData::getId));
        return successIndicatorData;
    }

    @Override
    public List<PapDataDto> getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType papGroupType, String measureFilter, String papName) {
        logger.info("Measure: {}, PAP NAME: {}", measureFilter, papName);
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
        List<PapData> finalPapDataList = papDataList
                .stream()
                .filter(papData -> papData.getSuccessIndicatorDataList().size() > 0)
                .filter(papData -> papData.getName()
                        .toLowerCase()
                        .contains(Optional.ofNullable(papName).orElse("")
                                .toLowerCase().trim()))
                .collect(Collectors.toList());
        return finalPapDataList
                .stream()
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }

    @Override
    public void updatePapData(List<PapDataDto> papDataDtoList) {
        papDataDtoList.forEach(
                papDataDto -> {
                    papDataDto.getSuccessIndicatorDataList().forEach(
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
        );
    }

    @Override
    public void updateSuccessIndicators(List<PapDataDto> papDataDtoList) {
        papDataDtoList.forEach(
                papDataDto -> {
                    papDataDto.getSuccessIndicatorDataList().forEach(
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
        );
    }

    private void setValues(SuccessIndicatorData successIndicatorData, SuccessIndicatorDataDto successIndicatorDataDto) {
        successIndicatorData.setIsAccumulated(successIndicatorDataDto.getIsAccumulated());
        successIndicatorData.setIsPercentage(successIndicatorDataDto.getIsPercentage());
        successIndicatorData.setIsDeleted(successIndicatorDataDto.getIsDeleted());
        successIndicatorData.setMeasures(successIndicatorDataDto.getMeasures());
        successIndicatorData.setTarget(successIndicatorDataDto.getTarget());
    }

    private void setValues(OperatingUnitData operatingUnitData, OperatingUnitDataDto operatingUnitDataDto) {
        operatingUnitData.setTarget(operatingUnitDataDto.getTarget());
        operatingUnitData.setOutput(operatingUnitDataDto.getOutput());
        operatingUnitData.setRate(ReportUtil.calculateRate(operatingUnitDataDto.getTarget(), operatingUnitDataDto.getOutput()));
    }

    private void calculateTotal(OperatingUnitDataDto total, OperatingUnitDataDto operatingUnitDataDto) {
        total.setOutput(total.getOutput() + operatingUnitDataDto.getOutput());
    }
}
