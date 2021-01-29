package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.CertificateType;
import com.tesda8.region8.util.enums.Sector;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class CertificateDto {

    private Long id;
    private Long trainerId;
    private String certificateNumber;
    private LocalDateTime dateIssued;
    private LocalDateTime expirationDate;
    private String clnNtcNumber;
    private String qualificationTitle;
    private CertificateType certificateType;
    private Boolean isDeleted;
    private Sector sector;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date dateIssuedRequest;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date expirationDateRequest;

}
