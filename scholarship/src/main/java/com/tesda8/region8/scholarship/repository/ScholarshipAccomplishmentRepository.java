package com.tesda8.region8.scholarship.repository;

import com.tesda8.region8.scholarship.model.entities.ScholarshipAccomplishment;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.ScholarshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarshipAccomplishmentRepository extends JpaRepository<ScholarshipAccomplishment, Long>, QuerydslPredicateExecutor<ScholarshipAccomplishment> {

    List<ScholarshipAccomplishment> findAllByYear(Long year);
    List<ScholarshipAccomplishment> findAllByYearAndMonthOrderById(Long year, Month month);
    List<ScholarshipAccomplishment> findAllByYearAndMonthAndScholarshipTypeOrderById(Long year, Month month, ScholarshipType scholarshipType);
    ScholarshipAccomplishment findByYearAndMonthAndScholarshipTypeAndOperatingUnitTypeOrderById(Long year, Month month,
                                                                                                ScholarshipType scholarshipType,
                                                                                                OperatingUnitType  operatingUnitType);
}
