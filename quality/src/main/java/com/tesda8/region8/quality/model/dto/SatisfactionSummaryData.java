package com.tesda8.region8.quality.model.dto;

import com.tesda8.region8.util.enums.FeedbackQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SatisfactionSummaryData {

    private String feedbackQuery;
    private Long verySatisfiedCount = 0L;
    private Long satisfiedCount = 0L;
    private Long poorCount = 0L;
}
