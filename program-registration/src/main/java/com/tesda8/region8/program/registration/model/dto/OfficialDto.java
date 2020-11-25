package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.EducationalAttainment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfficialDto {
    private Long id;
    private Long registeredProgramId;
    private String name;
    private String position;
    private String address;
    private String contactNumber;
    private String email;
    private String natureOfAppointment;
    private EducationalAttainment educationalAttainment;
    private Boolean isDeleted;

    public OfficialDto(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
