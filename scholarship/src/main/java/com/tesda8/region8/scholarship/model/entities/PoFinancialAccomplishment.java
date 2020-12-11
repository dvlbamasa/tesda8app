package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Embeddable
public class PoFinancialAccomplishment {

    private BigDecimal totalObligation;
    private BigDecimal obligationRate;
    private BigDecimal totalDisbursement;
    private BigDecimal disbursementRate;
}
