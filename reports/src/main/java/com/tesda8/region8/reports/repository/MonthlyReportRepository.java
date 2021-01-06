package com.tesda8.region8.reports.repository;

import com.tesda8.region8.reports.model.entities.MonthlyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyReportRepository extends JpaRepository<MonthlyReport, Long> {

    List<MonthlyReport> findAllByYear(int year);
}
