package com.tesda8.region8.web.service;

import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;

import javax.management.ServiceNotFoundException;

public interface ScholarshipGraphDataFetcherService {

    GraphDataList fetchMonthlyGraphDataList(Long year,
                                            EgacType egacType,
                                            OperatingUnitType operatingUnitType,
                                            ScholarshipType scholarshipType) throws ServiceNotFoundException;
}
