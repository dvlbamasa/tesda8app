package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.SortOrder;
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
public class ProgramRegistrationController {

    private InstitutionService institutionService;
    private static final String ALL = "ALL";


    @Autowired
    public ProgramRegistrationController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/program_registration")
    public String programRegistration(Model model) {
        return initializeModel(model);
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

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/courses/search")
    public String registeredProgramsWithFilter(@ModelAttribute RegisteredProgramFilter registeredProgramFilter,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        return initializeModelRegisteredPrograms(model, registeredProgramFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/create")
    public String createRegisteredProgram(Model model) {
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", new RegisteredProgramRequestDto());
        model.addAttribute("ttiList", ttiList);
        return "program_registration/add_prog_reg";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/institution/create")
    public String createInstitution(Model model) {
        model.addAttribute("institution", new InstitutionDto());
        return "program_registration/add_institution";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/update")
    public String updateRegisteredProgram(@PathVariable("id") Long id, Model model) {
        RegisteredProgramRequestDto registeredProgramRequestDto = institutionService.getRegisteredProgramDto(id);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", registeredProgramRequestDto);
        model.addAttribute("ttiList", ttiList);
        return "program_registration/update_prog_reg";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/institution/{id}/update")
    public String updateInstitution(@PathVariable("id") Long id, Model model) {
        InstitutionDto institutionDto = institutionService.getInstitutionDto(id);
        model.addAttribute("institution", institutionDto);
        return "program_registration/update_institution";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/create/save")
    public String saveRegisteredProgram(@ModelAttribute RegisteredProgramRequestDto registeredProgramRequestDto,
                                               BindingResult bindingResult,
                                               Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        institutionService.createRegisteredProgram(registeredProgramRequestDto);
        return initializeModel(model);
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

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/update/save")
    public String saveUpdatedRegisteredProgram(@ModelAttribute RegisteredProgramRequestDto registeredProgramRequestDto,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        institutionService.updateRegisteredProgram(registeredProgramRequestDto);
        return initializeModel(model);
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

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/delete")
    public String deleteRegisteredProgram(@PathVariable("id") Long id,
                                          Model model) {
        institutionService.deleteRegisteredProgram(id);
        return initializeModel(model);
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

    private String initializeModel(Model model) {
        RegisteredProgramFilter registeredProgramFilter = new RegisteredProgramFilter();
        registeredProgramFilter.setOperatingUnitType(new OperatingUnitType[]{OperatingUnitType.TOTAL});
        registeredProgramFilter.setInstitutionIds(new Long[]{0L});
        registeredProgramFilter.setInstitutionClassification(new InstitutionClassification[]{InstitutionClassification.ALL});
        registeredProgramFilter.setSector(Sector.ALL);
        registeredProgramFilter.setCourseStatus(CourseStatus.ALL);
        registeredProgramFilter.setCourseName("");
        registeredProgramFilter.setRegisteredProgramNumber("");
        registeredProgramFilter.setIsClosed(false);
        registeredProgramFilter.setSortOrder(SortOrder.ASC);
        return initializeModelRegisteredPrograms(model, registeredProgramFilter);
    }

    private String initializeModelRegisteredPrograms(Model model, RegisteredProgramFilter registeredProgramFilter) {
        List<RegisteredProgramDto> registeredProgramDtoList = institutionService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgramFilter", registeredProgramFilter);
        model.addAttribute("registeredPrograms", registeredProgramDtoList);
        model.addAttribute("ttiList", institutionDtoList);
        model.addAttribute("total", registeredProgramDtoList.size());
        return "program_registration/program_registration";
    }
}
