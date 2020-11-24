package com.tesda8.region8.reports.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.model.Auditable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class OperatingUnit extends Auditable<String> {

    @Enumerated(EnumType.STRING)
    private OperatingUnitType operatingUnitType;

    @OneToMany(mappedBy = "operatingUnit")
    @JsonManagedReference
    private List<MonthlyReport> monthlyReports;
}
