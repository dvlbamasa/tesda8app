package com.tesda8.region8.program.registration.model.wrapper;

import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import lombok.Data;

@Data
public class RegisteredProgramRequest {

    private OperatingUnitType operatingUnitType;
    private Sector sector;
}
