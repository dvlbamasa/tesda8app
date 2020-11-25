package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.EducationalAttainment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NonTeachingStaffDto {
    private Long id;
    private Long registeredProgramId;
    private String name;
    private String position;
    private String natureOfAppointment;
    private EducationalAttainment educationalAttainment;
    private String positionExperience;
    private Boolean isDeleted;

    public NonTeachingStaffDto(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
