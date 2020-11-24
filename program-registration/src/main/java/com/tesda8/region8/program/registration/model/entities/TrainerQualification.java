package com.tesda8.region8.program.registration.model.entities;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class TrainerQualification {

    private String nttcNumber;
    private LocalDateTime validity;
}
