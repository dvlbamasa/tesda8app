package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.DeliveryMode;
import com.tesda8.region8.util.enums.ReportSourceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ROPerModeReportDto {

    private long id;
    private DeliveryMode deliveryMode;
    private ReportSourceType reportSourceType;
    private EgacDataDto egacDataDto;
}
