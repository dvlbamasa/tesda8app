package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;

@Data
public class PhysicalAccomplishmentDto {
    private Long enrolled;
    private Double enrolledUtilization;
    private Long graduates;
    private Double graduatesUtilization;
    private Long assessed;
    private Double assessedUtilization;
    private Long certified;
    private Double certifiedUtilization;
    private Long employed;
}
