package com.tesda8.region8.program.registration.repository;

import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.OperatingUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    List<Institution> findAllByOperatingUnitType(OperatingUnitType operatingUnitType);

    List<Institution> findAllByInstitutionType(InstitutionType institutionType);
}
