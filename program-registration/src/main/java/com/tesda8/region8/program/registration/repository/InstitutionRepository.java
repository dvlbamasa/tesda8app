package com.tesda8.region8.program.registration.repository;

import com.tesda8.region8.program.registration.model.entities.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
