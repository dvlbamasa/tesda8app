package com.tesda8.region8.web.model.dto;

import com.tesda8.region8.util.enums.Month;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class MonthlyReportDto {

    private long id;
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String updatedBy;
    @Enumerated(EnumType.STRING)
    private Month month;
    private int year;
    private EgacDataDto egacDataDto;
}
