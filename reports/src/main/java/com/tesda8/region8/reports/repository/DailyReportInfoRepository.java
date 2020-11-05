package com.tesda8.region8.reports.repository;

import com.tesda8.region8.reports.model.entities.DailyReportInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReportInfoRepository extends JpaRepository<DailyReportInfo, Long> {
}
