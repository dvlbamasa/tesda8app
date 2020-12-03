package com.tesda8.region8.planning.repository;

import com.querydsl.core.types.Predicate;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.util.enums.PapGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PapDataRepository extends JpaRepository<PapData, Long>, QuerydslPredicateExecutor<PapData> {

    List<PapData> findAllByPapGroupType(PapGroupType papGroupType);

    List<PapData> findAllByYear(Long year);
}
