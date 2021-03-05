package com.tesda8.region8.certification.model.dto;

import com.tesda8.region8.util.enums.ExpiredCertificateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpiredCertificateFilter {

    private String trainerName;
    private ExpiredCertificateType expiredCertificateType;
}
