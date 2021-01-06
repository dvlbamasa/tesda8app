package com.tesda8.region8.reports.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.reports.model.entities.GeneralReport;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class GeneralReportAuditListener extends AbstractEntityListener<GeneralReport> {

    private ReportMapper reportMapper;

    @PostUpdate
    public void onUpdate(GeneralReport entity) {
        doUpdate(entity, AuditAction.UPDATE_REPORT_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(GeneralReport entity) {
        AuditLog auditLog = reportMapper.generalReportToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Entity Id", entity.getId());
        attributes.put("Operating Unit", entity.getOperatingUnitType().label);
        attributes.put("Report Type", entity.getDailyReportType().label);
        attributes.put("Report Source Type", entity.getReportSourceType().label);
        attributes.put("EGAC Type", entity.getEgacData().getEgacType().label);
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
