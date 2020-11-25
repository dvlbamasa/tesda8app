package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@NoArgsConstructor
public class CertificationRateReport extends Auditable<String> {

    @Column(name = "OPERATING_UNIT")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    private long assessed;

    private long certified;

    private double rate;
}
