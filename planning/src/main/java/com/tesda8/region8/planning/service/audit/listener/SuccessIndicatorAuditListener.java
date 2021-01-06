package com.tesda8.region8.planning.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import com.tesda8.region8.planning.model.mapper.PlanningMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class SuccessIndicatorAuditListener extends AbstractEntityListener<SuccessIndicatorData> {

    private PlanningMapper planningMapper;

    @PostPersist
    public void onCreate(SuccessIndicatorData entity) {
        doCreate(entity, AuditAction.CREATE_SUCCESS_INDICATOR_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(SuccessIndicatorData entity) {
        doUpdate(entity, AuditAction.UPDATE_SUCCESS_INDICATOR_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(SuccessIndicatorData entity) {
        AuditLog auditLog = planningMapper.successIndicatorDataToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Entity Id", entity.getId());
        attributes.put("P/A/P Name", entity.getPapData().getName());
        attributes.put("Target", entity.getTarget());
        attributes.put("Measure", entity.getMeasures());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
