package com.tesda8.region8.web.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.GeneralData;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "OPERATING_UNIT")
public class OperatingUnit extends GeneralData {

    @Column(name = "OPERATING_UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @OneToMany(mappedBy = "operatingUnit")
    @JsonManagedReference
    private List<MonthlyReport> monthlyReports;
}
