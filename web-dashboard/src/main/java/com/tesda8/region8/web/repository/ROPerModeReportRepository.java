package com.tesda8.region8.web.repository;

import com.tesda8.region8.web.model.entities.ROPerModeReport;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ROPerModeReportRepository extends JpaRepository<ROPerModeReport, Long> {

    List<ROPerModeReport> findAllByEgacData_EgacTypeAndReportSourceTypeOrderById(EgacType egacType,
                                                                                 ReportSourceType reportSourceType);

    List<ROPerModeReport> findAllByReportSourceTypeOrderById(ReportSourceType reportSourceType);
}
