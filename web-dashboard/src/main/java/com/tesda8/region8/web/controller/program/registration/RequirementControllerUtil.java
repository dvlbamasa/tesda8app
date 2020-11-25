package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.impl.RegisteredProgramService;
import org.springframework.ui.Model;

import java.util.List;

public class RequirementControllerUtil {

    private RegisteredProgramService registeredProgramService;
    private InstitutionService institutionService;

    public RequirementControllerUtil(RegisteredProgramService registeredProgramService,
                                     InstitutionService institutionService) {
        this.registeredProgramService = registeredProgramService;
        this.institutionService = institutionService;
    }

    protected String backToUpdateRegisteredProgram(Long id, Model model) {
        RegisteredProgramRequestDto registeredProgramRequestDto =
                registeredProgramService.getRegisteredProgramDto(id);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", registeredProgramRequestDto);
        model.addAttribute("ttiList", ttiList);
        return "program_registration/update_prog_reg";
    }
}
