package com.tesda8.region8.program.registration.model.dto;

import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerFilter {

    private String fullName;
    private Sex sex;
    private String address;
    private String contactNumber;
    private String emailAddress;
    private EducationalAttainment educationalAttainment;
}
