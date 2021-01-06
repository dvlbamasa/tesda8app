package com.tesda8.region8.program.registration.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class TrainerAuditListener extends AbstractEntityListener<Trainer> {

    private ProgramRegistrationMapper programRegistrationMapper;

    @PostPersist
    public void onCreate(Trainer entity) {
        doCreate(entity, AuditAction.CREATE_TRAINER_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(Trainer entity) {
        doUpdate(entity, AuditAction.UPDATE_TRAINER_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(Trainer entity) {
        AuditLog auditLog =  programRegistrationMapper.trainerToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Entity Id", entity.getId());
        attributes.put("Trainer Name", entity.getName());
        attributes.put("Registered Program Name", entity.getRegisteredProgram().getName());
        attributes.put("Program Registration Number", entity.getRegisteredProgram().getProgramRegistrationNumber());
        auditLog.setEntityAttributes(attributes);
        return auditLog;

    }
}
