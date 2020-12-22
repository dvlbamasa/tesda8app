package com.tesda8.region8.reports.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.reports.model.entities.TTIReport;
import com.tesda8.region8.reports.model.mapper.ReportMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class TTIReportAuditListener extends AbstractEntityListener<TTIReport> {

    private ReportMapper reportMapper;

    @PostUpdate
    public void onUpdate(TTIReport entity) {
        doUpdate(entity, AuditAction.UPDATE_REPORT_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(TTIReport entity) {
        AuditLog auditLog = reportMapper.ttiReportReportToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("tti", entity.getTtiType().label);
        attributes.put("reportType", "Per TTI");
        attributes.put("reportSourceType", entity.getReportSourceType().label);
        attributes.put("egacType", entity.getEgacData().getEgacType().label);
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
