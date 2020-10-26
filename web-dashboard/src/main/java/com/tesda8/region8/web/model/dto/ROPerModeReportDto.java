package com.tesda8.region8.web.model.dto;

import com.tesda8.region8.util.enums.DeliveryMode;
import com.tesda8.region8.util.enums.ReportSourceType;
import lombok.Data;

import java.util.Date;

@Data
public class ROPerModeReportDto {

    private long id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private DeliveryMode deliveryMode;
    private ReportSourceType reportSourceType;
    private EgacDataDto egacDataDto;
}
