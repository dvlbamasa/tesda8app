package com.tesda8.region8.reports.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.reports.model.entities.CertificationRateReport;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class CertificationRateAuditListener extends AbstractEntityListener<CertificationRateReport> {

    private ReportMapper reportMapper;

    @PostUpdate
    public void onUpdate(CertificationRateReport entity) {
        doUpdate(entity, AuditAction.UPDATE_REPORT_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(CertificationRateReport entity) {
        AuditLog auditLog = reportMapper.certificationRateReportToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Operating Unit", entity.getOperatingUnitType().label);
        attributes.put("Report Type", "Certification Rate");
        attributes.put("Report Source Type", "T2MIS");
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
