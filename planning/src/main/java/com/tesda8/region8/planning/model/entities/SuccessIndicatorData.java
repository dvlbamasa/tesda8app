package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.planning.service.audit.listener.SuccessIndicatorAuditListener;
import com.tesda8.region8.util.enums.SuccessIndicatorType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(SuccessIndicatorAuditListener.class)
public class SuccessIndicatorData extends Auditable<String> {

    private Integer target;

    private String measures;

    @Type(type = "yes_no")
    private Boolean isAccumulated = true;

    @Type(type = "yes_no")
    private Boolean isPercentage = false;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private SuccessIndicatorType successIndicatorType;

    @OneToMany(mappedBy = "successIndicatorData", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<OperatingUnitData> operatingUnitDataList;

    @ManyToOne
    @JoinColumn(name = "pap_data_id", nullable = false)
    @JsonManagedReference
    private PapData papData;

}
