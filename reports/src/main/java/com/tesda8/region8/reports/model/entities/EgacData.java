package com.tesda8.region8.reports.model.entities;

import com.tesda8.region8.util.enums.EgacType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
public class EgacData {

    @Enumerated(EnumType.STRING)
    @Column(name = "EGAC_TYPE")
    private EgacType egacType;

    @Column(name = "TARGET")
    private long target;

    @Column(name = "OUTPUT")
    private long output;

    @Column(name = "RATE")
    private double rate;
}
