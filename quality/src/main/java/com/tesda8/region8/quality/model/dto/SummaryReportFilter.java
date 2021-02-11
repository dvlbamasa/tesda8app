package com.tesda8.region8.quality.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryReportFilter {

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateFrom;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateTo;
}
