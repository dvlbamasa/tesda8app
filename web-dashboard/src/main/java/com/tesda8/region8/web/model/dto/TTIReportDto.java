package com.tesda8.region8.web.model.dto;

import com.tesda8.region8.util.enums.ReportSourceType;
import com.tesda8.region8.util.enums.TTIType;
import lombok.Data;

import java.util.Date;

@Data
public class TTIReportDto {

    private long id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    private TTIType ttiType;
    private ReportSourceType reportSourceType;
    private EgacDataDto egacDataDto;
}
