package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QualificationMapDto {
    private Long amount;
    private Long slots;

    public QualificationMapDto(Long amount, Long slots) {
        this.amount = amount;
        this.slots = slots;
    }
}
