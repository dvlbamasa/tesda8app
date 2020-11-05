package com.tesda8.region8.reports.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "DAILY_REPORT_INFO")
public class DailyReportInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}
