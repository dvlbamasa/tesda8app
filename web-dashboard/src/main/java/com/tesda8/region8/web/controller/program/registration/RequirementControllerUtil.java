package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import org.springframework.ui.Model;

import java.util.List;

public class RequirementControllerUtil {

    private InstitutionService institutionService;

    public RequirementControllerUtil(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    protected String backToUpdateRegisteredProgram(Long id, Model model) {
        RegisteredProgramRequestDto registeredProgramRequestDto =
                institutionService.getRegisteredProgramDto(id);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", registeredProgramRequestDto);
        model.addAttribute("ttiList", ttiList);
        return "program_registration/update_prog_reg";
    }
}
