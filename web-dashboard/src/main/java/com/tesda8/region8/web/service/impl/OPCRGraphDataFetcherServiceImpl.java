package com.tesda8.region8.web.service.impl;

import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.web.model.dto.graph.GraphDataList;
import com.tesda8.region8.web.service.OPCRGraphDataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OPCRGraphDataFetcherServiceImpl implements OPCRGraphDataFetcherService {

    private PapDataService papDataService;

    @Autowired
    public OPCRGraphDataFetcherServiceImpl(PapDataService papDataService) {
        this.papDataService = papDataService;
    }

    @Override
    public GraphDataList fetchOPCRDataList(Long successIndicatorId) {
        return null;
    }
}
