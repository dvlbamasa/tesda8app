package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.CertificateLayoutData;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.dto.TrainerFilter;

import java.util.List;

public interface TrainerService {

    TrainerDto createTrainer(TrainerDto trainerDto);

    void updateLinkedTrainer(TrainerDto trainerDto);

    void unlinkTrainer(Long id);

    List<TrainerDto> getAllTrainer();

    List<TrainerDto> getAllTrainerByFilter(TrainerFilter trainerFilter);

    CertificateLayoutData fetchCertificateLayout(Long trainerId, Long certificateId);
}
