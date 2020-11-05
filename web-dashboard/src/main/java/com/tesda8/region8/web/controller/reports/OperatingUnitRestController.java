package com.tesda8.region8.web.controller.reports;

import com.tesda8.region8.reports.model.dto.OperatingUnitDto;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.reports.service.OperatingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatingUnitRestController {

    private OperatingUnitService operatingUnitService;

    @Autowired
    public OperatingUnitRestController(OperatingUnitService operatingUnitService) {
        this.operatingUnitService = operatingUnitService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/operatingUnit/{operatingUnitType}/type")
    public OperatingUnitDto getOperatingUnit(@PathVariable("operatingUnitType") OperatingUnitType operatingUnitType) {
        return operatingUnitService.getOperatingUnit(operatingUnitType);
    }
}
