package com.tesda8.region8.web.model.entities;

import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CERTIFICATION_RATE_REPORT")
public class CertificationRateReport extends GeneralData {

    @Column(name = "OPERATING_UNIT")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @Column(name = "ASSESSED")
    private long assessed;

    @Column(name = "CERTIFIED")
    private long certified;

    @Column(name = "RATE")
    private double rate;
}
