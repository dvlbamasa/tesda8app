package com.tesda8.region8.program.registration.repository;

import com.tesda8.region8.program.registration.model.entities.NonTeachingStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NonTeachingStaffRepository extends JpaRepository<NonTeachingStaff, Long>, QuerydslPredicateExecutor<NonTeachingStaff> {
}
