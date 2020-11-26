package com.tesda8.region8.scholarship.service.impl;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.scholarship.model.mapper.ScholarshipMapper;
import com.tesda8.region8.scholarship.repository.ScholarshipAccomplishmentRepository;
import com.tesda8.region8.scholarship.service.ScholarshipAccomplishmentService;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.ScholarshipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScholarshipAccomplishmentServiceImpl implements ScholarshipAccomplishmentService {

    private ScholarshipAccomplishmentRepository scholarshipAccomplishmentRepository;
    private ScholarshipMapper scholarshipMapper;

    @Autowired
    public ScholarshipAccomplishmentServiceImpl(ScholarshipAccomplishmentRepository scholarshipAccomplishmentRepository,
                                                ScholarshipMapper scholarshipMapper) {
        this.scholarshipAccomplishmentRepository = scholarshipAccomplishmentRepository;
        this.scholarshipMapper = scholarshipMapper;
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishment() {
        return scholarshipAccomplishmentRepository.findAll()
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYear(Long year) {
        return scholarshipAccomplishmentRepository.findAllByYear(year)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYear(Long year, Month month) {
        return scholarshipAccomplishmentRepository.findAllByYearAndMonth(year, month)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByMonthAndYearAndType(Long year, Month month, ScholarshipType scholarshipType) {
        return scholarshipAccomplishmentRepository.findAllByYearAndMonthAndScholarshipType(year, month, scholarshipType)
                .stream()
                .map(scholarshipAccomplishment -> scholarshipMapper.scholarshipAccomplishmentToDto(scholarshipAccomplishment))
                .collect(Collectors.toList());
    }

    @Override
    public void updateScholarshipAccomplishment(List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList) {

    }
}
