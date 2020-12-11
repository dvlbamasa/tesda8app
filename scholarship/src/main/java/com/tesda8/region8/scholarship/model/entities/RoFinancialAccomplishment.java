package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Embeddable
public class RoFinancialAccomplishment {

    private BigDecimal amountFundsDownloadable;
    private BigDecimal adaAmount;
    private BigDecimal balance;
}
