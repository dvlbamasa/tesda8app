package com.tesda8.region8.audit.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tesda8.region8.audit.model.dto.AuditLogFilter;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.entities.QAuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.audit.repository.AuditLogRepository;
import com.tesda8.region8.audit.service.AuditLogService;
import com.tesda8.region8.util.service.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    private static Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);


    @Override
    public Page<AuditLog> findAll(int pageNumber, int pageSize, AuditLogFilter auditLogFilter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(QAuditLog.auditLog.updatedBy.containsIgnoreCase(
                Optional.ofNullable(auditLogFilter.getUpdatedBy()).orElse("")));
        if (auditLogFilter.getAuditDateFrom() != null) {
            booleanBuilder.and(QAuditLog.auditLog.updatedDate
                    .goe(ApplicationUtil.convertToLocalDateTimeViaInstant(auditLogFilter.getAuditDateFrom())));
        }
        if (auditLogFilter.getAuditDateTo() != null) {
            booleanBuilder.and(QAuditLog.auditLog.updatedDate
                    .loe(ApplicationUtil.convertToLocalDateTimeViaInstant(auditLogFilter.getAuditDateTo())));
        }
        if (auditLogFilter.getAuditAction() != AuditAction.ALL && auditLogFilter.getAuditAction() != null) {
            booleanBuilder.and(QAuditLog.auditLog.auditAction.eq(auditLogFilter.getAuditAction()));
        }

        Predicate predicate = booleanBuilder.getValue();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("auditDate").descending());
        return auditLogRepository.findAll(predicate, pageable);
    }

    @Override
    public AuditLog findAuditLogById(Long id) {
        return auditLogRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
