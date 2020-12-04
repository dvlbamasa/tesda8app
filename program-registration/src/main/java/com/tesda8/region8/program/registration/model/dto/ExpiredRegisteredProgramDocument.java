package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpiredRegisteredProgramDocument {

    private String programRegistrationNumber;
    private Long id;

    public ExpiredRegisteredProgramDocument(String programRegistrationNumber, Long id) {
        this.programRegistrationNumber = programRegistrationNumber;
        this.id = id;
    }
}
