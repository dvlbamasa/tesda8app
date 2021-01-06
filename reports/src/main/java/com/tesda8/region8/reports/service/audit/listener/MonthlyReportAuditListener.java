package com.tesda8.region8.reports.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.reports.model.entities.MonthlyReport;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReportAuditListener extends AbstractEntityListener<MonthlyReport> {

    private ReportMapper reportMapper;

    @PostPersist
    public void onUpdate(MonthlyReport entity) {
        doUpdate(entity, AuditAction.CREATE_MONTHLY_REPORT_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(MonthlyReport entity) {
        AuditLog auditLog = reportMapper.monthlyReportToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Entity Id", entity.getId());
        attributes.put("Operating Unit", entity.getOperatingUnit().getOperatingUnitType().label);
        attributes.put("Month", entity.getMonth());
        attributes.put("Year", entity.getYear());
        attributes.put("EGAC Type", entity.getEgacData().getEgacType());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
