package com.tesda8.region8.reports.service;

import com.tesda8.region8.reports.model.dto.OperatingUnitDto;
import com.tesda8.region8.util.enums.OperatingUnitType;

public interface OperatingUnitService {

    OperatingUnitDto getOperatingUnit(OperatingUnitType operatingUnitType);
}
