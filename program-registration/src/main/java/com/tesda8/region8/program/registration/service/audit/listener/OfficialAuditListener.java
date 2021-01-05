package com.tesda8.region8.program.registration.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.planning.service.impl.PapDataServiceImpl;
import com.tesda8.region8.program.registration.model.entities.Official;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class OfficialAuditListener extends AbstractEntityListener<Official> {
    private static Logger logger = LoggerFactory.getLogger(OfficialAuditListener.class);

    private ProgramRegistrationMapper programRegistrationMapper;

    @PostPersist
    public void onCreate(Official entity) {
        doCreate(entity, AuditAction.CREATE_OFFICIAL_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(Official entity) {
        doUpdate(entity, AuditAction.UPDATE_OFFICIAL_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(Official entity) {
        AuditLog auditLog = programRegistrationMapper.officialToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Official's Name", entity.getName());
        attributes.put("Official's Contact Number", entity.getContactNumber());
        attributes.put("Registered Program Name", entity.getRegisteredProgram().getName());
        attributes.put("Program Registration Number", entity.getRegisteredProgram().getProgramRegistrationNumber());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
