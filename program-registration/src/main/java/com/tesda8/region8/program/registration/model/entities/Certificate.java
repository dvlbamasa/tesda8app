package com.tesda8.region8.program.registration.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.program.registration.service.audit.listener.CertificateAuditListener;
import com.tesda8.region8.util.enums.CertificateType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.model.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(CertificateAuditListener.class)
@Entity
public class Certificate extends Auditable<String> {

    private String certificateNumber;
    private LocalDateTime dateIssued;
    private LocalDateTime expirationDate;
    private String qualificationTitle;
    private String clnNtcNumber;
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;

    @Type(type = "yes_no")
    private Boolean isExpired = false;

    @ManyToOne
    @JoinColumn(name = "TRAINER_ID", nullable = false)
    @JsonManagedReference
    private Trainer trainer;
}
