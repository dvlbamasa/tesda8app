package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.DeliveryMode;
import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "RO_PER_MODE_REPORT")
public class ROPerModeReport extends GeneralData {

    @Embedded
    private EgacData egacData;

    @Column(name = "DELIVERY_MODE")
    @Enumerated(EnumType.STRING)
    private DeliveryMode deliveryMode;

    @Column(name = "REPORT_SOURCE_TYPE")
    @Enumerated(EnumType.STRING)
    private ReportSourceType reportSourceType;
}
