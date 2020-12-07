package com.tesda8.region8.web.controller.program.registration;

import com.google.common.collect.Lists;
import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.NonTeachingStaffDto;
import com.tesda8.region8.program.registration.model.dto.OfficialDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.RegisteredProgramService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.SortOrder;
import com.tesda8.region8.web.controller.DefaultController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProgramRegistrationController extends DefaultController {

    private InstitutionService institutionService;
    private RegisteredProgramService registeredProgramService;
    private static Logger logger = LoggerFactory.getLogger(ProgramRegistrationController.class);

    @Autowired
    public ProgramRegistrationController(InstitutionService institutionService,
                                         RegisteredProgramService registeredProgramService,
                                         RegisteredProgramStatusService registeredProgramStatusService) {
        super(registeredProgramStatusService);
        this.institutionService = institutionService;
        this.registeredProgramService = registeredProgramService;
    }

    @GetMapping("/program_registration")
    public String programRegistration(Model model) {
        initializeModel(model);
        return "program_registration/program_registration";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/search")
    public String registeredProgramsWithFilter(@ModelAttribute RegisteredProgramFilter registeredProgramFilter,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgramFilter", registeredProgramFilter);
        model.addAttribute("registeredPrograms", registeredProgramDtoList);
        model.addAttribute("ttiList", institutionDtoList);
        model.addAttribute("total", registeredProgramDtoList.size());
        addExpiredDocumentsListToModel(model);
        return "program_registration/program_registration";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/create")
    public String createRegisteredProgram(Model model) {
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", new RegisteredProgramRequestDto());
        model.addAttribute("ttiList", ttiList);
        addStatusCounterToModel(model);
        return "program_registration/add_prog_reg";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/update")
    public String updateRegisteredProgram(@PathVariable("id") Long id, Model model) {
        RegisteredProgramRequestDto registeredProgramRequestDto = registeredProgramService.getRegisteredProgramDto(id);
        List<InstitutionDto> ttiList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgram", registeredProgramRequestDto);
        model.addAttribute("ttiList", ttiList);
        addStatusCounterToModel(model);
        return "program_registration/update_prog_reg";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/create/save")
    public String saveRegisteredProgram(@ModelAttribute RegisteredProgramRequestDto registeredProgramRequestDto,
                                               BindingResult bindingResult,
                                               Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        RegisteredProgram registeredProgram = registeredProgramService.createRegisteredProgram(registeredProgramRequestDto);
        registeredProgramRequestDto.setOfficialDtoList(Lists.newArrayList());
        registeredProgramRequestDto.setTrainerDtoList(Lists.newArrayList());
        registeredProgramRequestDto.setNonTeachingStaffDtoList(Lists.newArrayList());
        registeredProgramRequestDto.getOfficialDtoList().add(new OfficialDto(false));
        registeredProgramRequestDto.getTrainerDtoList().add(new TrainerDto(false));
        registeredProgramRequestDto.getNonTeachingStaffDtoList().add(new NonTeachingStaffDto(false));
        registeredProgramRequestDto.setId(registeredProgram.getId());
        model.addAttribute("registeredProgram", registeredProgramRequestDto);
        addStatusCounterToModel(model);
        return "program_registration/add_other_requirements";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/create/saveRequirements")
    public String saveRegisteredProgramRequirements(@ModelAttribute RegisteredProgramRequestDto registeredProgramRequestDto,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registeredProgramService.saveRegisteredProgramRequirements(registeredProgramRequestDto);
        return "redirect:/program_registration";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/update/save")
    public String saveUpdatedRegisteredProgram(@ModelAttribute RegisteredProgramRequestDto registeredProgramRequestDto,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registeredProgramService.updateRegisteredProgram(registeredProgramRequestDto);
        return "redirect:/program_registration";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/delete")
    public String deleteRegisteredProgram(@PathVariable("id") Long id,
                                          Model model) {
        registeredProgramService.deleteRegisteredProgram(id);
        return "redirect:/program_registration";

    }

    private void initializeModel(Model model) {
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

        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgramFilter", registeredProgramFilter);
        model.addAttribute("registeredPrograms", registeredProgramDtoList);
        model.addAttribute("ttiList", institutionDtoList);
        model.addAttribute("total", registeredProgramDtoList.size());
        addStatusCounterToModel(model);
        addExpiredDocumentsListToModel(model);
    }
}
