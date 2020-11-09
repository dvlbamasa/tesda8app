package com.tesda8.region8.planning.repository;

import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.util.enums.PapGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PapDataRepository extends JpaRepository<PapData, Long> {

    List<PapData> findAllByPapGroupType(PapGroupType papGroupType);
}
