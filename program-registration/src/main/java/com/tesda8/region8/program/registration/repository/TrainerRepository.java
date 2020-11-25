package com.tesda8.region8.program.registration.repository;

import com.tesda8.region8.program.registration.model.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>, QuerydslPredicateExecutor<Trainer> {
}
