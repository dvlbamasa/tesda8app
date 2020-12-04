package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.repository.TrainerRepository;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Qualifier("trainer")
public class TrainerServiceImpl implements RegistrationRequirementsCrudService<TrainerDto> {

    private TrainerRepository trainerRepository;
    private RegisteredProgramRepository registeredProgramRepository;
    private ProgramRegistrationMapper programRegistrationMapper;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository,
                              RegisteredProgramRepository registeredProgramRepository,
                              ProgramRegistrationMapper programRegistrationMapper) {
        this.trainerRepository = trainerRepository;
        this.registeredProgramRepository = registeredProgramRepository;
        this.programRegistrationMapper = programRegistrationMapper;
    }

    @Override
    @Transactional
    public void create(TrainerDto dto) {
        RegisteredProgram registeredProgram = registeredProgramRepository
                .findById(dto.getRegisteredProgramId()).orElseThrow(EntityNotFoundException::new);
        Trainer trainer = programRegistrationMapper.trainerToEntity(dto);
        trainer.setRegisteredProgram(registeredProgram);
        trainer.setIsDeleted(false);
        registeredProgram.getTrainerList().add(trainer);
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public void update(TrainerDto dto) {
        Trainer trainer = trainerRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        programRegistrationMapper.updatedTrainerToEntity(dto,trainer);
        trainerRepository.save(trainer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        trainer.setIsDeleted(true);
        trainerRepository.save(trainer);
    }

    @Override
    public TrainerDto get(Long id) {
        return programRegistrationMapper.trainerToDto(trainerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }
}
