package com.tesda8.region8.scholarship.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public PhysicalAccomplishmentDto(Long enrolled, Double enrolledUtilization, Long graduates, Double graduatesUtilization, Long assessed, Double assessedUtilization, Long certified, Double certifiedUtilization, Long employed) {
        this.enrolled = enrolled;
        this.enrolledUtilization = enrolledUtilization;
        this.graduates = graduates;
        this.graduatesUtilization = graduatesUtilization;
        this.assessed = assessed;
        this.assessedUtilization = assessedUtilization;
        this.certified = certified;
        this.certifiedUtilization = certifiedUtilization;
        this.employed = employed;
    }
}
