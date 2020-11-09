package com.tesda8.region8.planning.repository;

import com.tesda8.region8.planning.model.entities.OperatingUnitData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingUnitDataRepository extends JpaRepository<OperatingUnitData, Long> {
}
