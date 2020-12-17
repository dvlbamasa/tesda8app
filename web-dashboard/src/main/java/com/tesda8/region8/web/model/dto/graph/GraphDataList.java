package com.tesda8.region8.web.model.dto.graph;

import lombok.Data;

@Data
public class GraphDataList {

    private GraphData targetData;
    private GraphData outputData;
    private GraphData rateData;

    public GraphDataList build() {
        GraphDataList graphDataList = new GraphDataList();
        graphDataList.setRateData(new GraphData());
        graphDataList.setTargetData(new GraphData());
        graphDataList.setOutputData(new GraphData());
        return graphDataList;
    }
}
