package com.tesda8.region8.planning.repository;

import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessIndicatorDataRepository extends JpaRepository<SuccessIndicatorData, Long>, QuerydslPredicateExecutor<SuccessIndicatorData> {
}
