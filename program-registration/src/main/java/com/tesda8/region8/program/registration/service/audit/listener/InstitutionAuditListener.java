package com.tesda8.region8.program.registration.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class InstitutionAuditListener extends AbstractEntityListener<Institution> {

    private ProgramRegistrationMapper programRegistrationMapper;

    @PostPersist
    public void onCreate(Institution entity) {
        doCreate(entity, AuditAction.CREATE_INSTITUTION_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(Institution entity) {
        doUpdate(entity, AuditAction.UPDATE_INSTITUTION_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(Institution entity) {
        AuditLog auditLog = programRegistrationMapper.institutionToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Entity Id", entity.getId());
        attributes.put("Institution Name", entity.getName());
        attributes.put("Short Name", entity.getShortName());
        attributes.put("Operating Unit", entity.getOperatingUnitType().label);
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
