package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class FinancialAccomplishment {

    private RoFinancialAccomplishment roFinancialAccomplishment;
    private PoFinancialAccomplishment poFinancialAccomplishment;
}
