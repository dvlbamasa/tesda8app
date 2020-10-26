package com.tesda8.region8.web.service;

import com.tesda8.region8.web.model.dto.OperatingUnitDto;
import com.tesda8.region8.util.enums.OperatingUnitType;

public interface OperatingUnitService {

    OperatingUnitDto getOperatingUnit(OperatingUnitType operatingUnitType);
}
