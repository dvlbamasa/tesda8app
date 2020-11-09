package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.enums.OperatingUnitPOType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "OperatingUnitData")
public class OperatingUnitData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "OPERATING_UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private OperatingUnitPOType operatingUnitType;

    @Column(name = "TARGET")
    private Long target;

    @Column(name = "OUTPUT")
    private Long output;

    @Column(name = "RATE")
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "success_indicator_data_id", nullable = false)
    @JsonBackReference
    private SuccessIndicatorData successIndicatorData;
}