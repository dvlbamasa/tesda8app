package com.tesda8.region8.reports.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.reports.service.audit.listener.MonthlyReportAuditListener;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(MonthlyReportAuditListener.class)
public class MonthlyReport extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operating_unit_id", nullable = false)
    @JsonBackReference
    private OperatingUnit operatingUnit;

    @Enumerated(EnumType.STRING)
    private Month month;

    private int year;

    @Embedded
    private EgacData egacData;
}
