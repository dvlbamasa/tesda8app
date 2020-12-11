package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RoFinancialAccomplishmentDto {
    private BigDecimal amountFundsDownloadable;
    private BigDecimal adaAmount;
    private BigDecimal balance;

    public RoFinancialAccomplishmentDto(BigDecimal amountFundsDownloadable, BigDecimal adaAmount, BigDecimal balance) {
        this.amountFundsDownloadable = amountFundsDownloadable;
        this.adaAmount = adaAmount;
        this.balance = balance;
    }
}
