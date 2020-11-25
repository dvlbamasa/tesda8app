package com.tesda8.region8.program.registration.service.impl;

import com.tesda8.region8.program.registration.model.dto.NonTeachingStaffDto;
import com.tesda8.region8.program.registration.model.entities.NonTeachingStaff;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.mapper.ProgramRegistrationMapper;
import com.tesda8.region8.program.registration.repository.NonTeachingStaffRepository;
import com.tesda8.region8.program.registration.repository.RegisteredProgramRepository;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Qualifier("staff")
public class NonTeachingStaffServiceImpl implements RegistrationRequirementsCrudService<NonTeachingStaffDto> {

    private NonTeachingStaffRepository nonTeachingStaffRepository;
    private RegisteredProgramRepository registeredProgramRepository;
    private ProgramRegistrationMapper programRegistrationMapper;

    @Autowired
    public NonTeachingStaffServiceImpl(NonTeachingStaffRepository nonTeachingStaffRepository,
                                       RegisteredProgramRepository registeredProgramRepository,
                                       ProgramRegistrationMapper programRegistrationMapper) {
        this.nonTeachingStaffRepository = nonTeachingStaffRepository;
        this.registeredProgramRepository = registeredProgramRepository;
        this.programRegistrationMapper = programRegistrationMapper;
    }

    @Override
    @Transactional
    public void create(NonTeachingStaffDto dto) {
        RegisteredProgram registeredProgram = registeredProgramRepository
                .findById(dto.getRegisteredProgramId()).orElseThrow(EntityNotFoundException::new);
        NonTeachingStaff nonTeachingStaff = programRegistrationMapper.nonTeachingStaffToEntity(dto);
        nonTeachingStaff.setRegisteredProgram(registeredProgram);
        registeredProgram.getNonTeachingStaffList().add(nonTeachingStaff);
        registeredProgramRepository.save(registeredProgram);
    }

    @Override
    @Transactional
    public void update(NonTeachingStaffDto dto) {
        NonTeachingStaff nonTeachingStaff = nonTeachingStaffRepository.findById(dto.getId())
                .orElseThrow(EntityNotFoundException::new);
        programRegistrationMapper.updatedNonTeachingStaff(dto, nonTeachingStaff);
        nonTeachingStaffRepository.save(nonTeachingStaff);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        NonTeachingStaff nonTeachingStaff = nonTeachingStaffRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        nonTeachingStaff.setIsDeleted(true);
        nonTeachingStaffRepository.save(nonTeachingStaff);
    }

    @Override
    public NonTeachingStaffDto get(Long id) {
        return programRegistrationMapper.nonTeachingStaffToDto(
                nonTeachingStaffRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
