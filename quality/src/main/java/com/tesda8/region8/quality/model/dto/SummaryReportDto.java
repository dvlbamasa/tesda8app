package com.tesda8.region8.quality.model.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SummaryReportDto {
    List<SummaryData> numberOfClientsByGender = Lists.newArrayList();
    List<SummaryData> numberOfClientsByAgeGroup = Lists.newArrayList();
    List<SummaryData> numberOfClientsByNatureOfInquiry = Lists.newArrayList();
    List<SummaryData> numberOfClientsByActionProvided = Lists.newArrayList();
    List<SatisfactionSummaryData> satisfactionData = Lists.newArrayList();
    List<SummaryData> overallRatingData = Lists.newArrayList();
}
