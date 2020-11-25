package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.EgacType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@NoArgsConstructor
public class EgacData {

    @Enumerated(EnumType.STRING)
    private EgacType egacType;
    private long target;
    private long output;
    private double rate;
}
