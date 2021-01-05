package com.tesda8.region8.planning.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import com.tesda8.region8.planning.model.mapper.PlanningMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class OperatingUnitDataAuditListener extends AbstractEntityListener<OperatingUnitData> {

    private PlanningMapper planningMapper;

    @PostUpdate
    public void onUpdate(OperatingUnitData entity) {
        doUpdate(entity, AuditAction.UPDATE_OPCR_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(OperatingUnitData entity) {
        AuditLog auditLog = planningMapper.operatingUnitDataToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Operating Unit", entity.getOperatingUnitType().label);
        attributes.put("P/A/P Name", entity.getSuccessIndicatorData().getPapData().getName());
        attributes.put("P/A/P Group Type", entity.getSuccessIndicatorData().getPapData().getPapGroupType());
        attributes.put("Target", entity.getTarget());
        attributes.put("Output", entity.getOutput());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
