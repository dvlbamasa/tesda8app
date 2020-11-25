package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FinancialAccomplishmentDto {
    private Long totalObligation;
    private Double obligationRate;
    private Long totalDisbursement;
    private Double disbursementRate;
}
