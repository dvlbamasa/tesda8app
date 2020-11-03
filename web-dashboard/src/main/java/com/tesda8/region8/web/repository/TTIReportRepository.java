package com.tesda8.region8.web.repository;

import com.tesda8.region8.util.enums.EgacType;
import com.tesda8.region8.web.model.entities.TTIReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TTIReportRepository extends JpaRepository<TTIReport, Long> {

    List<TTIReport> findAllByEgacData_EgacTypeOrderById(EgacType egacType);

}
