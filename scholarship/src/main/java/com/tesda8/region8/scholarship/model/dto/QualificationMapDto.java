package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class QualificationMapDto {
    private BigDecimal amount;
    private Long slots;

    public QualificationMapDto(BigDecimal amount, Long slots) {
        this.amount = amount;
        this.slots = slots;
    }
}
