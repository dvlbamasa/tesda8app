package com.tesda8.region8.program.registration.model.mapper;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.NonTeachingStaffDto;
import com.tesda8.region8.program.registration.model.dto.OfficialDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.entities.Institution;
import com.tesda8.region8.program.registration.model.entities.NonTeachingStaff;
import com.tesda8.region8.program.registration.model.entities.Official;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.model.entities.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgramRegistrationMapper {
    ProgramRegistrationMapper INSTANCE = Mappers.getMapper(ProgramRegistrationMapper.class);

    RegisteredProgram registeredProgramToEntity(RegisteredProgramDto programDto);
    RegisteredProgram registeredProgramToEntity(RegisteredProgramRequestDto registeredProgramRequestDto);

    @Mapping(source = "registeredProgram.institution.name", target = "institutionName")
    @Mapping(source = "registeredProgram.institution.institutionClassification", target = "institutionClassification")
    @Mapping(source = "registeredProgram.institution.operatingUnitType", target = "operatingUnit")
    @Mapping(source = "registeredProgram.institution.shortName", target = "institutionShortName")
    RegisteredProgramDto registeredProgramToDto(RegisteredProgram registeredProgram);

    @Mapping(source = "registeredProgram.trainerList", target = "trainerDtoList")
    @Mapping(source = "registeredProgram.officialList", target = "officialDtoList")
    @Mapping(source = "registeredProgram.nonTeachingStaffList", target = "nonTeachingStaffDtoList")
    RegisteredProgramRequestDto registeredProgramToRequestDto(RegisteredProgram registeredProgram);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateIssued", ignore = true)
    public abstract RegisteredProgram updatedRegisteredProgramToEntity(RegisteredProgramRequestDto registeredProgramDto, @MappingTarget RegisteredProgram registeredProgram);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "dateIssued", ignore = true)
    @Mapping(target = "programRegistrationNumber", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "courseStatus", ignore = true)
    @Mapping(target = "numberOfTeachers", ignore = true)
    @Mapping(target = "isClosed", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "registrationRequirement", ignore = true)
    @Mapping(source = "registeredProgramDto.trainerDtoList", target = "trainerList")
    @Mapping(source = "registeredProgramDto.officialDtoList", target = "officialList")
    @Mapping(source = "registeredProgramDto.nonTeachingStaffDtoList", target = "nonTeachingStaffList")
    public abstract RegisteredProgram updatedRegisteredProgramRequirementsToEntity(RegisteredProgramRequestDto registeredProgramDto, @MappingTarget RegisteredProgram registeredProgram);

    Institution institutionToEntity(InstitutionDto institutionDto);
    InstitutionDto institutionToDto(Institution institution);

    @Mapping(target = "registeredPrograms", ignore = true)
    public abstract Institution updatedInstitutionToEntity(InstitutionDto institutionDto, @MappingTarget Institution institution);

    Official officialToEntity(OfficialDto officialDto);
    @Mapping(source = "official.registeredProgram.id", target = "registeredProgramId")
    OfficialDto officialToDto(Official official);
    @Mapping(target = "isDeleted", ignore = true)
    public abstract Official updatedOfficialToEntity(OfficialDto officialDto, @MappingTarget Official official);

    Trainer trainerToEntity(TrainerDto trainerDto);
    @Mapping(source = "trainer.registeredProgram.id", target = "registeredProgramId")
    TrainerDto trainerToDto(Trainer trainer);
    @Mapping(target = "isDeleted", ignore = true)
    public abstract Trainer updatedTrainerToEntity(TrainerDto trainerDto, @MappingTarget Trainer trainer);

    NonTeachingStaff nonTeachingStaffToEntity(NonTeachingStaffDto nonTeachingStaffDto);
    @Mapping(source = "nonTeachingStaff.registeredProgram.id", target = "registeredProgramId")
    NonTeachingStaffDto nonTeachingStaffToDto(NonTeachingStaff nonTeachingStaff);
    @Mapping(target = "isDeleted", ignore = true)
    public abstract NonTeachingStaff updatedNonTeachingStaff(NonTeachingStaffDto nonTeachingStaffDto, @MappingTarget NonTeachingStaff nonTeachingStaff);
}
