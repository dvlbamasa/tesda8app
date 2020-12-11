package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class FinancialAccomplishmentDto {
    private RoFinancialAccomplishmentDto roFinancialAccomplishmentDto;
    private PoFinancialAccomplishmentDto poFinancialAccomplishmentDto;

    public FinancialAccomplishmentDto(RoFinancialAccomplishmentDto roFinancialAccomplishmentDto, PoFinancialAccomplishmentDto poFinancialAccomplishmentDto) {
        this.roFinancialAccomplishmentDto = roFinancialAccomplishmentDto;
        this.poFinancialAccomplishmentDto = poFinancialAccomplishmentDto;
    }
}
