package com.tesda8.region8.audit.model;

import com.tesda8.region8.audit.model.enums.AuditAction;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class AuditBase {

    @Enumerated(EnumType.STRING)
    private AuditAction auditAction;
    private LocalDateTime auditDate;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
