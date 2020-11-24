package com.tesda8.region8.scholarship.repository;

import com.tesda8.region8.scholarship.model.entities.ScholarshipAccomplishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipAccomplishmentRepository extends JpaRepository<ScholarshipAccomplishment, Long>, QuerydslPredicateExecutor<ScholarshipAccomplishment> {
}
