package com.tesda8.region8.audit.service;

import com.tesda8.region8.audit.model.dto.AuditLogFilter;
import com.tesda8.region8.audit.model.entities.AuditLog;
import org.springframework.data.domain.Page;

public interface AuditLogService {

    Page<AuditLog> findAll(int pageNumber, int pageSize, AuditLogFilter auditLogFilter);

    AuditLog findAuditLogById(Long id);
}
