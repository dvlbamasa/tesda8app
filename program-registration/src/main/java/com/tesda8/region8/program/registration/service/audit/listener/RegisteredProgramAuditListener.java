package com.tesda8.region8.program.registration.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class RegisteredProgramAuditListener extends AbstractEntityListener<RegisteredProgram> {

    private ProgramRegistrationMapper programRegistrationMapper;

    @PostPersist
    public void onCreate(RegisteredProgram entity) {
        doCreate(entity, AuditAction.CREATE_REGISTERED_PROGRAM_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(RegisteredProgram entity) {
        doUpdate(entity, AuditAction.UPDATE_REGISTERED_PROGRAM_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(RegisteredProgram entity) {
        AuditLog auditLog =  programRegistrationMapper.registeredProgramToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Id", entity.getId());
        attributes.put("Registered Program Name", entity.getName());
        attributes.put("Program Registration Number", entity.getProgramRegistrationNumber());
        attributes.put("Institution Name", entity.getInstitution().getName());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
