package com.tesda8.region8.reports.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "MONTHLY_REPORT")
public class MonthlyReport extends GeneralData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operating_unit_id", nullable = false)
    @JsonBackReference
    private OperatingUnit operatingUnit;

    @Column(name = "MONTH")
    @Enumerated(EnumType.STRING)
    private Month month;

    @Column(name = "YEAR")
    private int year;

    @Embedded
    private EgacData egacData;
}
