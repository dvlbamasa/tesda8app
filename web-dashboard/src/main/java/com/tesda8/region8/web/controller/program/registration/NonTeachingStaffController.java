package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.NonTeachingStaffDto;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.web.controller.DefaultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NonTeachingStaffController extends DefaultController {

    private RegistrationRequirementsCrudService<NonTeachingStaffDto> registrationRequirementsCrudService;

    @Autowired
    public NonTeachingStaffController(RegisteredProgramStatusService registeredProgramStatusService,
                                      @Qualifier("staff") RegistrationRequirementsCrudService registrationRequirementsCrudService) {
        super(registeredProgramStatusService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/staff/create")
    public String createStaff(@PathVariable("id") Long id, Model model) {
        NonTeachingStaffDto nonTeachingStaffDto = new NonTeachingStaffDto();
        nonTeachingStaffDto.setRegisteredProgramId(id);
        model.addAttribute("staff", nonTeachingStaffDto);
        addStatusCounterToModel(model);
        return "program_registration/staff/add_staff";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/staff/{id}/update")
    public String updateStaff(@PathVariable("id") Long id, Model model) {
        NonTeachingStaffDto nonTeachingStaffDto = registrationRequirementsCrudService.get(id);
        model.addAttribute("staff", nonTeachingStaffDto);
        addStatusCounterToModel(model);
        return "program_registration/staff/update_staff";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/staff/create/save")
    public String createStaffSave(@ModelAttribute NonTeachingStaffDto nonTeachingStaffDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.create(nonTeachingStaffDto);
        return "redirect:/program_registration/registeredProgram/" + nonTeachingStaffDto.getRegisteredProgramId() + "/update";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/staff/update/save")
    public String updateStaffSave(@ModelAttribute NonTeachingStaffDto nonTeachingStaffDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.update(nonTeachingStaffDto);
        return "redirect:/program_registration/registeredProgram/" + nonTeachingStaffDto.getRegisteredProgramId() + "/update";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/{registeredProgramId}/staff/{id}/delete")
    public String deleteTrainer(@PathVariable("id") Long id,
                                @PathVariable("registeredProgramId") Long registeredProgramId, Model model) {
        registrationRequirementsCrudService.delete(id);
        return "redirect:/program_registration/registeredProgram/" + registeredProgramId + "/update";
    }
}
