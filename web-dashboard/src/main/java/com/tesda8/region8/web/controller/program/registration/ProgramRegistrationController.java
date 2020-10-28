package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.model.wrapper.RegisteredProgramRequest;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class ProgramRegistrationController {

    private InstitutionService institutionService;

    @Autowired
    public ProgramRegistrationController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/institutions")
    public String showInstitutionsProgRegCount(Model model) {
        ProgramRegistrationWrapper programRegistrationWrapper = institutionService.getCourseCountPerInstitution();
        model.addAttribute("institutionsList", programRegistrationWrapper);
        return "program_registration/prog_reg_dashboard";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/institutions/{sectorType}/sector")
    public String getCoursesPerSector(@PathVariable("sectorType") Sector sector, Model model) {
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitutionByCourseSector(sector);
        model.addAttribute("operatingUnitType", OperatingUnitType.TOTAL);
        model.addAttribute("sectorValue", sector);
        model.addAttribute("registeredProgramRequest", new RegisteredProgramRequest());
        model.addAttribute("institutions", institutionDtoList);
        return "program_registration/prog_reg_list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/institutions/search")
    public String getInstitutions(@ModelAttribute RegisteredProgramRequest registeredProgramRequest,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        List<InstitutionDto> institutionDtoList = registeredProgramRequest.getOperatingUnitType().equals(OperatingUnitType.TOTAL) ?
                institutionService.getAllInstitutionByCourseSector(registeredProgramRequest.getSector()) :
                institutionService.getAllInstitutionByOperatingUnitAndSector(registeredProgramRequest.getOperatingUnitType(),
                        registeredProgramRequest.getSector());
        model.addAttribute("operatingUnitType", registeredProgramRequest.getOperatingUnitType());
        model.addAttribute("sectorValue", registeredProgramRequest.getSector());
        model.addAttribute("registeredProgramRequest", new RegisteredProgramRequest());
        model.addAttribute("institutions", institutionDtoList);
        return "program_registration/prog_reg_list";
    }

}
