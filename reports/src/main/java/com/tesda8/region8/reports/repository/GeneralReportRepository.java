package com.tesda8.region8.reports.repository;

import com.tesda8.region8.reports.model.entities.GeneralReport;
import com.tesda8.region8.util.enums.DailyReportType;
import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.util.enums.ReportSourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralReportRepository extends JpaRepository<GeneralReport, Long> {

    List<GeneralReport> findAllByEgacData_EgacTypeAndAndDailyReportTypeOrderById(EgacType egacType,
                                                                                 DailyReportType dailyReportType);

    List<GeneralReport> findAllByDailyReportTypeOrderById(DailyReportType dailyReportType);

    List<GeneralReport> findAllByDailyReportTypeAndReportSourceTypeOrderById(DailyReportType dailyReportType,
                                                                    ReportSourceType reportSourceType);

    List<GeneralReport> findAllByDailyReportTypeAndReportSourceTypeAndEgacData_EgacTypeOrderById(DailyReportType dailyReportType,
                                                                                        ReportSourceType reportSourceType,
                                                                                        EgacType egacType);
}
