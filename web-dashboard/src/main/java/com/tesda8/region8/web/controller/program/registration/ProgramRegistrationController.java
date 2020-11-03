package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionProgramRegCounter;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.model.wrapper.RegisteredProgramRequest;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(ProgramRegistrationController.class);
    private static final String ALL = "ALL";

    private InstitutionService institutionService;

    @Autowired
    public ProgramRegistrationController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registeredPrograms")
    public String showInstitutionsProgRegCount(Model model) {
        ProgramRegistrationWrapper programRegistrationWrapper = institutionService.getCourseCountPerInstitution();
        model.addAttribute("institutionsList", programRegistrationWrapper);
        return "program_registration/prog_reg_dashboard";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registeredPrograms/{sectorType}/sector")
    public String getCoursesPerSector(@PathVariable("sectorType") Sector sector, Model model) {
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitutionByCourseSector(sector);
        List<InstitutionDto> ttiList = institutionService.getAllInstitutionByInstitutionType(InstitutionType.PUBLIC);
        InstitutionProgramRegCounter institutionProgramRegCounter = institutionService.getTotalCountOfRegisteredPrograms(institutionDtoList);
        model.addAttribute("sectorValue", sector);
        model.addAttribute("registeredProgramRequest", new RegisteredProgramRequest());
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("ttiList", ttiList);
        model.addAttribute("total", institutionProgramRegCounter);
        return "program_registration/prog_reg_list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registeredPrograms/search")
    public String getInstitutions(@ModelAttribute RegisteredProgramRequest registeredProgramRequest,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        // handles bootstrap select bug not including ALL option
        if (registeredProgramRequest.getInstitutionNames().length == 0) {
            registeredProgramRequest.setInstitutionNames(new String[] {ALL});
        }
        List<InstitutionDto> institutionDtoList =
                institutionService.getAllInstitutionByNameAndSectorAndCourseName(registeredProgramRequest.getInstitutionNames(),
                        registeredProgramRequest.getSector(), registeredProgramRequest.getCourseName());
        List<InstitutionDto> ttiList = institutionService.getAllInstitutionByInstitutionType(InstitutionType.PUBLIC);
        InstitutionProgramRegCounter institutionProgramRegCounter = institutionService.getTotalCountOfRegisteredPrograms(institutionDtoList);
        model.addAttribute("sectorValue", registeredProgramRequest.getSector());
        model.addAttribute("courseNameValue", registeredProgramRequest.getCourseName());
        model.addAttribute("registeredProgramRequest", new RegisteredProgramRequest());
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("ttiList", ttiList);
        model.addAttribute("total", institutionProgramRegCounter);
        return "program_registration/prog_reg_list";
    }

}
