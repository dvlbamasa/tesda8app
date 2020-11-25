package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class PhysicalAccomplishment {

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
