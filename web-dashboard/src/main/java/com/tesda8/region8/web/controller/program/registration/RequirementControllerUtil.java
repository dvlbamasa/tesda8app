package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.RegisteredProgramService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.web.controller.DefaultController;
import org.springframework.ui.Model;

import java.util.List;

public class RequirementControllerUtil extends DefaultController {

    private RegisteredProgramService registeredProgramService;
    private InstitutionService institutionService;

    public RequirementControllerUtil(RegisteredProgramService registeredProgramService,
                                     InstitutionService institutionService,
                                     RegisteredProgramStatusService registeredProgramStatusService) {
        super(registeredProgramStatusService);
        this.registeredProgramService = registeredProgramService;
        this.institutionService = institutionService;
    }

    protected String backToUpdateRegisteredProgram(Long id, Model model) {
        RegisteredProgramRequestDto registeredProgramRequestDto =
                registeredProgramService.getRegisteredProgramDto(id);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", registeredProgramRequestDto);
        model.addAttribute("ttiList", ttiList);
        addStatusCounterToModel(model);
        return "program_registration/update_prog_reg";
    }
}
