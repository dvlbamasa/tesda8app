package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.enums.TTIType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TTI_REPORT")
public class TTIReport extends GeneralData {

    @Embedded
    private EgacData egacData;

    @Column(name = "TTI_TYPE")
    @Enumerated(EnumType.STRING)
    private TTIType ttiType;

    @Column(name = "REPORT_SOURCE_TYPE")
    @Enumerated(EnumType.STRING)
    private ReportSourceType reportSourceType;
}
