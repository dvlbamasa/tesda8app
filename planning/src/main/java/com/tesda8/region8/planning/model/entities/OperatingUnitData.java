package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "OperatingUnitData")
public class OperatingUnitData extends GeneralData {

    @Column(name = "OPERATING_UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @Column(name = "TARGET")
    private long target;

    @Column(name = "OUTPUT")
    private long output;

    @Column(name = "RATE")
    private double rate;

    @ManyToOne
    @JoinColumn(name = "success_indicator_data_id", nullable = false)
    @JsonBackReference
    private SuccessIndicatorData successIndicatorData;
}
