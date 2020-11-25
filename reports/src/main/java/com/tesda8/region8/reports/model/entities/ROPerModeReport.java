package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.DeliveryMode;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "RO_PER_MODE_REPORT")
public class ROPerModeReport extends Auditable<String> {

    @Embedded
    private EgacData egacData;

    @Enumerated(EnumType.STRING)
    private DeliveryMode deliveryMode;

    @Enumerated(EnumType.STRING)
    private ReportSourceType reportSourceType;
}
