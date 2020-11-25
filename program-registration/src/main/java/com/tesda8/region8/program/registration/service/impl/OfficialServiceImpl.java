package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.OfficialDto;
import com.tesda8.region8.program.registration.model.entities.Official;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.repository.OfficialRepository;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Qualifier("official")
public class OfficialServiceImpl implements RegistrationRequirementsCrudService<OfficialDto> {

    private OfficialRepository officialRepository;
    private RegisteredProgramRepository registeredProgramRepository;
    private ProgramRegistrationMapper programRegistrationMapper;

    @Autowired
    public OfficialServiceImpl(OfficialRepository officialRepository,
                               RegisteredProgramRepository registeredProgramRepository,
                               ProgramRegistrationMapper programRegistrationMapper) {
        this.officialRepository = officialRepository;
        this.registeredProgramRepository = registeredProgramRepository;
        this.programRegistrationMapper = programRegistrationMapper;
    }

    @Override
    @Transactional
    public void create(OfficialDto officialDto) {
        RegisteredProgram registeredProgram = registeredProgramRepository
                .findById(officialDto.getRegisteredProgramId()).orElseThrow(EntityNotFoundException::new);
        Official official = programRegistrationMapper.officialToEntity(officialDto);
        official.setRegisteredProgram(registeredProgram);
        registeredProgram.getOfficialList().add(official);
        registeredProgramRepository.save(registeredProgram);
    }

    @Override
    @Transactional
    public void update(OfficialDto officialDto) {
        Official official = officialRepository.findById(officialDto.getId()).orElseThrow(EntityNotFoundException::new);
        programRegistrationMapper.updatedOfficialToEntity(officialDto, official);
        officialRepository.save(official);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Official official = officialRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        official.setIsDeleted(true);
        officialRepository.save(official);
    }

    @Override
    public OfficialDto get(Long id) {
        return programRegistrationMapper.officialToDto(officialRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }
}
