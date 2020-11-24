package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class SuccessIndicatorData extends Auditable<String> {

    private Integer target;

    private String measures;

    @Type(type = "yes_no")
    private Boolean isAccumulated = true;

    @Type(type = "yes_no")
    private Boolean isPercentage = false;

    @Type(type = "yes_no")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "successIndicatorData", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OperatingUnitData> operatingUnitDataList;

    @ManyToOne
    @JoinColumn(name = "pap_data_id", nullable = false)
    @JsonBackReference
    private PapData papData;

}
