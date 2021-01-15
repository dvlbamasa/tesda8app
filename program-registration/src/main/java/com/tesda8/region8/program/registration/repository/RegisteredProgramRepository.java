package com.tesda8.region8.program.registration.repository;

import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredProgramRepository extends JpaRepository<RegisteredProgram, Long>, QuerydslPredicateExecutor<RegisteredProgram> {

    @Query("select rp from RegisteredProgram rp join rp.officialList rpos join rp.trainerList rpts where rp.id = :id and rpos.isDeleted = false and rpts.isDeleted = false")
    @EntityGraph(attributePaths = {"officialList", "trainerList"}, type = EntityGraph.EntityGraphType.LOAD)
    RegisteredProgram getByIdNotDeleted(@Param("id") Long id);
}
