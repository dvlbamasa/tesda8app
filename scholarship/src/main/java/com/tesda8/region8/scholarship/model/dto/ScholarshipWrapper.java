package com.tesda8.region8.scholarship.model.dto;

import com.google.common.collect.Lists;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ScholarshipWrapper {

    private Long year;
    private Month month;
    private List<ScholarshipAccomplishmentDto> twspData;
    private List<ScholarshipAccomplishmentDto> pesfaData;
    private List<ScholarshipAccomplishmentDto> stepData;
    private List<ScholarshipAccomplishmentDto> respData;
    private List<ScholarshipAccomplishmentDto> uaqteaSbData;
    private List<ScholarshipAccomplishmentDto> uaqteaDiplomaData;

    public ScholarshipWrapper initializeScholarshipWrapper(Month month, Long year) {
        this.month = month;
        this.year = year;
        this.twspData = Lists.newArrayList();
        this.pesfaData = Lists.newArrayList();
        this.stepData = Lists.newArrayList();
        this.respData = Lists.newArrayList();
        this.uaqteaSbData = Lists.newArrayList();
        this.uaqteaDiplomaData = Lists.newArrayList();
        Arrays.asList(OperatingUnitType.values()).forEach(
                operatingUnitType -> {
                    Arrays.asList(ScholarshipType.values()).forEach(
                            scholarshipType -> {
                                switch (scholarshipType) {
                                    case TWSP:
                                        this.twspData.add(new ScholarshipAccomplishmentDto(operatingUnitType,scholarshipType, month, year));
                                        break;
                                    case PESFA:
                                        this.pesfaData.add(new ScholarshipAccomplishmentDto(operatingUnitType, scholarshipType, month, year));
                                        break;
                                    case STEP:
                                        this.stepData.add(new ScholarshipAccomplishmentDto(operatingUnitType, scholarshipType, month, year));
                                        break;
                                    case RESP:
                                        this.respData.add(new ScholarshipAccomplishmentDto(operatingUnitType, scholarshipType, month, year));
                                        break;
                                    case UAQTEA_SB:
                                        this.uaqteaSbData.add(new ScholarshipAccomplishmentDto(operatingUnitType, scholarshipType, month, year));
                                        break;
                                    case UAQTEA_DIPLOMA:
                                        this.uaqteaDiplomaData.add(new ScholarshipAccomplishmentDto(operatingUnitType, scholarshipType, month, year));
                                        break;
                                    default:
                                        break;
                                }
                            }
                    );
                }
        );
        return this;
    }
}
