package com.tesda8.region8.audit.model.dto;

import com.tesda8.region8.audit.model.enums.AuditAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogFilter {

    private String updatedBy;
    private AuditAction auditAction;
    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    private Date auditDateFrom;
    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    private Date auditDateTo;
}
