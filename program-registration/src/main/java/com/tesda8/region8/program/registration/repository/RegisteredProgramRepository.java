package com.tesda8.region8.program.registration.repository;

import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredProgramRepository extends JpaRepository<RegisteredProgram, Long>, QuerydslPredicateExecutor<RegisteredProgram> {
}
