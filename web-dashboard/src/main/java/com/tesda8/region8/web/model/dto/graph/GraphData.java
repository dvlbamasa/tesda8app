package com.tesda8.region8.web.model.dto.graph;

import com.tesda8.region8.util.model.DataPoints;
import lombok.Data;

import java.util.List;

@Data
public class GraphData {

    private List<DataPoints> dataPoints;
}
