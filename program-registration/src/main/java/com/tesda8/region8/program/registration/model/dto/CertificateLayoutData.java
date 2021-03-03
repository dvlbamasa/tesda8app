package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CertificateLayoutData {

    private String fullName;
    private boolean longName = false;
    private String qualification;
    private String certificateNumber;
    private String issuedOn;
    private String expirationDate;
}
