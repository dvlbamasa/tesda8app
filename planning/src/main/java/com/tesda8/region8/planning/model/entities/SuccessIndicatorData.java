package com.tesda8.region8.planning.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;
import org.hibernate.annotations.Type;

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
public class SuccessIndicatorData extends GeneralData {

    @Column(name = "TARGET")
    private int target;

    @Column(name = "MEASURES")
    private String measures;

    @Column(name = "IS_ACCUMULATED")
    @Type(type = "yes_no")
    private boolean isAccumulated = false;

    @OneToMany(mappedBy = "successIndicatorData")
    @JsonManagedReference
    List<OperatingUnitData> operatingUnitDataList;

    @ManyToOne
    @JoinColumn(name = "pap_data_id", nullable = false)
    @JsonBackReference
    private PapData papData;

}
