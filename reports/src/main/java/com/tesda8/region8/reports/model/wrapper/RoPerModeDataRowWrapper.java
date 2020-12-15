package com.tesda8.region8.reports.model.wrapper;

import com.tesda8.region8.reports.model.dto.ROPerModeReportDto;
import com.tesda8.region8.util.enums.DeliveryMode;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RoPerModeDataRowWrapper {

    private DeliveryMode deliveryMode;
    private ROPerModeReportDto enrolled;
    private ROPerModeReportDto graduates;
}
