package com.tesda8.region8.audit.model.entities;

import com.tesda8.region8.audit.model.AuditBase;
import com.tesda8.region8.audit.service.HashMapConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class AuditLog extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long entityId;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> entityAttributes;

    @Transient
    public String getAuditLogDetails() {
        StringBuilder details = new StringBuilder();
        for (Map.Entry<String, Object> entry : entityAttributes.entrySet()) {
            details.append(entry.getKey()).append(": ").append(entry.getValue());
            details.append(" \n ");
        }
        return details.toString();
    }
}
