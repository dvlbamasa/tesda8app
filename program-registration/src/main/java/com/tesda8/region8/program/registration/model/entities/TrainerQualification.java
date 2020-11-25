package com.tesda8.region8.program.registration.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Embeddable
public class TrainerQualification {

    private String nttcNumber;
    private LocalDateTime validity;
}
