package com.tesda8.region8.reports.service.impl;

import com.tesda8.region8.reports.model.dto.OperatingUnitDto;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import com.tesda8.region8.reports.repository.OperatingUnitRepository;
import com.tesda8.region8.reports.service.OperatingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatingUnitServiceImpl implements OperatingUnitService {

    private OperatingUnitRepository operatingUnitRepository;
    private ReportMapper reportMapper;

    @Autowired
    public OperatingUnitServiceImpl(OperatingUnitRepository operatingUnitRepository, ReportMapper reportMapper) {
        this.operatingUnitRepository = operatingUnitRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public OperatingUnitDto getOperatingUnit(OperatingUnitType operatingUnitType) {
        return reportMapper.operatingUnitToDto(operatingUnitRepository.getByOperatingUnitTypeOrderById(operatingUnitType));
    }
}
