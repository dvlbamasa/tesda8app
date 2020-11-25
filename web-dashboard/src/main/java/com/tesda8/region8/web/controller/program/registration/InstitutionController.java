package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class InstitutionController {

    private InstitutionService institutionService;

    @Autowired
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/program_registration/institutions")
    public String institutionsList(Model model) {
        return initializeModelInstitutionPage(model);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/institutions/filter")
    public String institutionListWithFilter(@ModelAttribute InstitutionFilter institutionFilter,
                                            BindingResult bindingResult,
                                            Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitutionWithFilter(institutionFilter);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("institutionFilter", institutionFilter);
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("ttiList", ttiList);
        return "program_registration/institution_list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/institution/create")
    public String createInstitution(Model model) {
        model.addAttribute("institution", new InstitutionDto());
        return "program_registration/add_institution";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/institution/{id}/update")
    public String updateInstitution(@PathVariable("id") Long id, Model model) {
        InstitutionDto institutionDto = institutionService.getInstitutionDto(id);
        model.addAttribute("institution", institutionDto);
        return "program_registration/update_institution";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/institution/create/save")
    public String saveInstitution(@ModelAttribute InstitutionDto institutionDto,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        institutionService.createInstitution(institutionDto);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", new RegisteredProgramRequestDto());
        model.addAttribute("ttiList", ttiList);
        return "program_registration/add_prog_reg";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/institution/update/save")
    public String saveUpdatedInstitution(@ModelAttribute InstitutionDto institutionDto,
                                         BindingResult bindingResult,
                                         Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        institutionService.updateInstitution(institutionDto);
        return initializeModelInstitutionPage(model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/institution/{id}/delete")
    public String deleteInstitution(@PathVariable("id") Long id, Model model) {
        institutionService.deleteInstitution(id);
        return initializeModelInstitutionPage(model);
    }

    private String initializeModelInstitutionPage(Model model) {
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        model.addAttribute("institutionFilter", new InstitutionFilter());
        model.addAttribute("contactNumber", "");
        model.addAttribute("address", "");
        model.addAttribute("institutionName", "");
        model.addAttribute("institutions", institutionDtoList);
        model.addAttribute("ttiList", institutionDtoList);
        return "program_registration/institution_list";
    }

}
