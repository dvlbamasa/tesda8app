package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "SUCCESS_INDICATOR_DATA")
public class SuccessIndicatorData extends Auditable<String> {

    @Column(name = "TARGET")
    private Integer target;

    @Column(name = "MEASURES")
    private String measures;

    @Column(name = "IS_ACCUMULATED")
    @Type(type = "yes_no")
    private Boolean isAccumulated = true;

    @Column(name = "IS_PERCENTAGE")
    @Type(type = "yes_no")
    private Boolean isPercentage = false;

    @Column(name = "IS_DELETED")
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
