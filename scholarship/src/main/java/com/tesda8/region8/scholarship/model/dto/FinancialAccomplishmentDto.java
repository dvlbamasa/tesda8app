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


    public FinancialAccomplishmentDto(Long totalObligation, Double obligationRate, Long totalDisbursement, Double disbursementRate) {
        this.totalObligation = totalObligation;
        this.obligationRate = obligationRate;
        this.totalDisbursement = totalDisbursement;
        this.disbursementRate = disbursementRate;
    }
}
