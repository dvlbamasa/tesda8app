package com.tesda8.region8.web.repository;

import com.tesda8.region8.web.model.entities.CertificationRateReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRateReportRepository extends JpaRepository<CertificationRateReport, Long> {
}
