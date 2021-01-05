package com.tesda8.region8.scholarship.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.scholarship.model.entities.ScholarshipAccomplishment;
import com.tesda8.region8.scholarship.model.mapper.ScholarshipMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipAccomplishmentAuditListener extends AbstractEntityListener<ScholarshipAccomplishment> {

    private ScholarshipMapper scholarshipMapper;

    @PostPersist
    public void onCreate(ScholarshipAccomplishment entity) {
        doCreate(entity, AuditAction.CREATE_SCHOLARSHIP_ENTITY_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(ScholarshipAccomplishment entity) {
        doUpdate(entity, AuditAction.UPDATE_SCHOLARSHIP_ENTITY_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(ScholarshipAccomplishment entity) {
        AuditLog auditLog =  scholarshipMapper.scholarshipAccomplishmentToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Scholarship Type", entity.getScholarshipType());
        attributes.put("Operating Unit", entity.getOperatingUnitType().label);
        attributes.put("Month", entity.getMonth());
        attributes.put("Year", entity.getYear());
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
