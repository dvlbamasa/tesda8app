package com.tesda8.region8.quality.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SummaryData {
    private String label;
    private String type;
    private Long count = 0L;
    private Double percentage = 0.0;
}
