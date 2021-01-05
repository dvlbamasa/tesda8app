package com.tesda8.region8.program.registration.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.program.registration.model.entities.NonTeachingStaff;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class NonTeachingStaffAuditListener extends AbstractEntityListener<NonTeachingStaff> {

    private ProgramRegistrationMapper programRegistrationMapper;

    @PostPersist
    public void onCreate(NonTeachingStaff entity) {
        doCreate(entity, AuditAction.CREATE_STAFF_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(NonTeachingStaff entity) {
        doUpdate(entity, AuditAction.UPDATE_STAFF_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(NonTeachingStaff entity) {
        AuditLog auditLog =  programRegistrationMapper.nonTeachingStaffToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Non-Teaching Staff Name", entity.getName());
        attributes.put("Registered Program Name", entity.getRegisteredProgram().getName());
        attributes.put("Program Registration Number", entity.getRegisteredProgram().getProgramRegistrationNumber());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
