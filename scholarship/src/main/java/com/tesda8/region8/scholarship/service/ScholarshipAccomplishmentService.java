package com.tesda8.region8.scholarship.service;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.ScholarshipType;

import java.util.List;

public interface ScholarshipAccomplishmentService {

    List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishment();

    List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYear(Long year);

    List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYear(Long year, Month month);

    List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYearAndType(Long year,
                                                                                            Month month,
                                                                                            ScholarshipType scholarshipType);
    void updateScholarshipAccomplishment(List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList);
}
