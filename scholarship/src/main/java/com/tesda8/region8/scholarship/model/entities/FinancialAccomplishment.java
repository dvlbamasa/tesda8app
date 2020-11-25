package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class FinancialAccomplishment {

    private Long totalObligation;
    private Double obligationRate;
    private Long totalDisbursement;
    private Double disbursementRate;
}
