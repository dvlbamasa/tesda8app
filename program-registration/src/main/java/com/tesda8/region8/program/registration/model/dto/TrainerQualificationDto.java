package com.tesda8.region8.program.registration.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class TrainerQualificationDto {
    private String nttcNumber;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date validity;
}
