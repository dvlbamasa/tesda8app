package com.tesda8.region8.certification.repository;

import com.tesda8.region8.program.registration.model.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long>, QuerydslPredicateExecutor<Certificate> {
}
