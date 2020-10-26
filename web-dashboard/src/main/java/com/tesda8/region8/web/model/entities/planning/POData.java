package com.tesda8.region8.web.model.entities.planning;

import com.tesda8.region8.web.model.entities.GeneralData;
import com.tesda8.region8.util.enums.OperatingUnitType;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class POData extends GeneralData {

    private OperatingUnitType operatingUnitType;
}
