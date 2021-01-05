package com.tesda8.region8.planning.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.planning.model.mapper.PlanningMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class PapDataAuditListener extends AbstractEntityListener<PapData> {

    private PlanningMapper planningMapper;

    @PostPersist
    public void onCreate(PapData entity) {
        doCreate(entity, AuditAction.CREATE_PAP_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(PapData entity) {
        doUpdate(entity, AuditAction.UPDATE_PAP_ENTITY_AUDIT_EVENT);
    }


    @Override
    public AuditBase mapAuditEntity(PapData entity) {
        AuditLog auditLog = planningMapper.papDataToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("P/A/P Name", entity.getName());
        attributes.put("P/A/P Group Type", entity.getPapGroupType());
        attributes.put("Year", entity.getYear());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
