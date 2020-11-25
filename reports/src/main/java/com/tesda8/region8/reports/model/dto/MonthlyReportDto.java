package com.tesda8.region8.reports.model.dto;

import com.tesda8.region8.util.enums.Month;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@NoArgsConstructor
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
