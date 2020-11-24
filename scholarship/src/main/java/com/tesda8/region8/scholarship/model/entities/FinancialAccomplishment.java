package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class FinancialAccomplishment {

    private Long totalObligation;
    private Double obligationRate;
    private Long totalDisbursement;
    private Double disbursementRate;
}
