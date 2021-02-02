package com.tesda8.region8.program.registration.model.entities;

import com.tesda8.region8.util.enums.NatureOfAppointmentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Embeddable
@NoArgsConstructor
public class NatureOfAppointmentDetails {

    @Enumerated(EnumType.STRING)
    private NatureOfAppointmentType natureOfAppointmentType;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date validity;
}
