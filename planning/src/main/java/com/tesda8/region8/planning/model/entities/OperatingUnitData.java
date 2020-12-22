package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tesda8.region8.planning.service.audit.listener.OperatingUnitDataAuditListener;
import com.tesda8.region8.util.enums.OperatingUnitPOType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(OperatingUnitDataAuditListener.class)
public class OperatingUnitData extends Auditable<String> {

    @Enumerated(EnumType.STRING)
    private OperatingUnitPOType operatingUnitType;

    private Long target;

    private Long output;

    private Double rate;

    @ManyToOne
    @JoinColumn(name = "success_indicator_data_id", nullable = false)
    @JsonBackReference
    private SuccessIndicatorData successIndicatorData;
}
