package com.tesda8.region8.planning.service.impl;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.entities.PapData;
import com.tesda8.region8.planning.model.mapper.PlanningMapper;
import com.tesda8.region8.planning.repository.PapDataRepository;
import com.tesda8.region8.planning.service.PapDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PapDataServiceImpl implements PapDataService {

    private PapDataRepository papDataRepository;
    private PlanningMapper planningMapper;

    @Autowired
    public PapDataServiceImpl(PapDataRepository papDataRepository, PlanningMapper planningMapper) {
        this.papDataRepository = papDataRepository;
        this.planningMapper = planningMapper;
    }

    @Override
    public List<PapDataDto> getAllPapData() {
        List<PapData> papDataList = papDataRepository.findAll();
        return papDataList
                .stream()
                .map(papData -> planningMapper.papDataToDto(papData))
                .collect(Collectors.toList());
    }
}
