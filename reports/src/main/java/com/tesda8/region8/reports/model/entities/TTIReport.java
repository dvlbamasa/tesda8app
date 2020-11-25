package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.enums.TTIType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TTI_REPORT")
public class TTIReport extends Auditable<String> {

    @Embedded
    private EgacData egacData;

    @Enumerated(EnumType.STRING)
    private TTIType ttiType;

    @Enumerated(EnumType.STRING)
    private ReportSourceType reportSourceType;
}
