package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PoFinancialAccomplishmentDto {

    private BigDecimal totalObligation;
    private BigDecimal obligationRate;
    private BigDecimal totalDisbursement;
    private BigDecimal disbursementRate;

    public PoFinancialAccomplishmentDto(BigDecimal totalObligation, BigDecimal obligationRate, BigDecimal totalDisbursement, BigDecimal disbursementRate) {
        this.totalObligation = totalObligation;
        this.obligationRate = obligationRate;
        this.totalDisbursement = totalDisbursement;
        this.disbursementRate = disbursementRate;
    }
}
