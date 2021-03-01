package com.tesda8.region8.web.service;

import com.tesda8.region8.web.model.dto.graph.GraphDataList;

public interface OPCRGraphDataFetcherService {

    GraphDataList fetchOPCRDataList(Long successIndicatorId, String pageType);
}
