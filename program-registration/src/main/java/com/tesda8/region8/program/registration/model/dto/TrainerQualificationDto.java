package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainerQualificationDto {
    private String nttcNumber;
    private LocalDateTime validity;
}
