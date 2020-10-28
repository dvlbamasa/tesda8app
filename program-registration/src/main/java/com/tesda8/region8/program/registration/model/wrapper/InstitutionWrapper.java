package com.tesda8.region8.program.registration.model.wrapper;

import com.google.common.collect.Lists;
import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;

import java.util.List;

@Data
public class InstitutionWrapper {

    private OperatingUnitType operatingUnitType;
    private String institutionName;
    private String institutionShortName;
    private List<CourseCount> courseCountList = Lists.newArrayList();
}
