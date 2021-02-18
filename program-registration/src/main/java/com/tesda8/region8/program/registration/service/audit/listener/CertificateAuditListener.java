package com.tesda8.region8.program.registration.service.audit.listener;

import com.tesda8.region8.audit.listener.AbstractEntityListener;
import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.program.registration.model.entities.Certificate;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.util.service.ApplicationUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class CertificateAuditListener extends AbstractEntityListener<Certificate> {

    private ProgramRegistrationMapper programRegistrationMapper;

    @PostPersist
    public void onCreate(Certificate entity) {
        doCreate(entity, AuditAction.CREATE_CERTIFICATE_AUDIT_EVENT);
    }

    @PostUpdate
    public void onUpdate(Certificate entity) {
        doUpdate(entity, AuditAction.UPDATE_CERTIFICATE_AUDIT_EVENT);
    }

    @Override
    public AuditBase mapAuditEntity(Certificate entity) {
        AuditLog auditLog =  programRegistrationMapper.certificateToAudit(entity);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Entity Id", entity.getId());
        attributes.put("Certificate Number", entity.getCertificateNumber());
        attributes.put("Date Issued", ApplicationUtil.formatLocalDateTimeToString(entity.getDateIssued()));
        attributes.put("Expiration Date", ApplicationUtil.formatLocalDateTimeToString(entity.getExpirationDate()));
        attributes.put("Certificate Type", entity.getCertificateType().label);
        auditLog.setEntityAttributes(attributes);
        return auditLog;
    }
}
