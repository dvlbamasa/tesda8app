package com.tesda8.region8.scholarship.model.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class QualificationMap {

    private Long amount;
    private Long slots;
}
