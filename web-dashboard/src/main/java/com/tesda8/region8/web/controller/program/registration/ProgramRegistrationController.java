package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.wrapper.InstitutionProgramRegCounter;
import com.tesda8.region8.program.registration.model.wrapper.RegisteredProgramRequest;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProgramRegistrationController {

    private InstitutionService institutionService;
    private static final String ALL = "ALL";


    @Autowired
    public ProgramRegistrationController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/program_registration")
    public String programRegistration(Model model) {
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        InstitutionProgramRegCounter institutionProgramRegCounter = institutionService.getTotalCountOfRegisteredPrograms(institutionDtoList);
        model.addAttribute("courseNameValue", "");
        model.addAttribute("registeredProgramFilter", new RegisteredProgramFilter());
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("ttiList", institutionDtoList);
        model.addAttribute("total", institutionProgramRegCounter);
        return "program_registration/program_registration";
    }

    @GetMapping("/program_registration/institutions")
    public String institutionsList(Model model) {
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        InstitutionProgramRegCounter institutionProgramRegCounter = institutionService.getTotalCountOfRegisteredPrograms(institutionDtoList);
        model.addAttribute("institutionFilter", new InstitutionFilter());
        model.addAttribute("contactNumber", "");
        model.addAttribute("address", "");
        model.addAttribute("institutionName", "");
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("total", institutionProgramRegCounter);
        return "program_registration/institution_list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/institutions/filter")
    public String institutionListWithFilter(@ModelAttribute InstitutionFilter institutionFilter,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitutionWithFilter(institutionFilter);
        InstitutionProgramRegCounter institutionProgramRegCounter = institutionService.getTotalCountOfRegisteredPrograms(institutionDtoList);
        model.addAttribute("institutionFilter", institutionFilter);
        model.addAttribute("contactNumber", institutionFilter.getContactNumber());
        model.addAttribute("address", institutionFilter.getAddress());
        model.addAttribute("institutionName", institutionFilter.getInstitutionName());
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("total", institutionProgramRegCounter);
        return "program_registration/institution_list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/courses/search")
    public String registeredProgramsWithFilter(@ModelAttribute RegisteredProgramFilter registeredProgramFilter,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        // handles bootstrap select bug not including ALL option
        if (registeredProgramFilter.getInstitutionNames().length == 0) {
            registeredProgramFilter.setInstitutionNames(new String[] {ALL});
        }
        List<InstitutionDto> institutionDtoList =
                institutionService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        InstitutionProgramRegCounter institutionProgramRegCounter = institutionService.getTotalCountOfRegisteredPrograms(institutionDtoList);
        model.addAttribute("courseNameValue", registeredProgramFilter.getCourseName());
        model.addAttribute("registeredProgramFilter", new RegisteredProgramFilter());
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("ttiList", ttiList);
        model.addAttribute("total", institutionProgramRegCounter);
        return "program_registration/program_registration";
    }

}
