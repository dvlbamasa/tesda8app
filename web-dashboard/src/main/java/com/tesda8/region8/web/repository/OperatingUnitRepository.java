package com.tesda8.region8.web.repository;

import com.tesda8.region8.web.model.entities.OperatingUnit;
import com.tesda8.region8.util.enums.OperatingUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingUnitRepository extends JpaRepository<OperatingUnit, Long> {

    OperatingUnit getByOperatingUnitTypeOrderById(OperatingUnitType operatingUnitType);
}
