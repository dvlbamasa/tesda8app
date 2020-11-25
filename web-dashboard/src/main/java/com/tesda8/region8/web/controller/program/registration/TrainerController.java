package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.program.registration.service.impl.RegisteredProgramService;
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
public class TrainerController extends RequirementControllerUtil{

    private RegistrationRequirementsCrudService<TrainerDto> registrationRequirementsCrudService;

    @Autowired
    public TrainerController(@Qualifier("trainer") RegistrationRequirementsCrudService registrationRequirementsCrudService,
                             InstitutionService institutionService,
                             RegisteredProgramService registeredProgramService) {
        super(registeredProgramService, institutionService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/trainer/create")
    public String createTrainer(@PathVariable("id") Long id, Model model) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setRegisteredProgramId(id);
        model.addAttribute("trainer", trainerDto);
        return "program_registration/trainer/add_trainer";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/trainer/{id}/update")
    public String updateTrainer(@PathVariable("id") Long id, Model model) {
        TrainerDto trainerDto = registrationRequirementsCrudService.get(id);
        model.addAttribute("trainer", trainerDto);
        return "program_registration/trainer/update_trainer";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/trainer/create/save")
    public String createTrainerSave(@ModelAttribute TrainerDto trainerDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.create(trainerDto);
        return backToUpdateRegisteredProgram(trainerDto.getRegisteredProgramId(), model);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/trainer/update/save")
    public String updateTrainerSave(@ModelAttribute TrainerDto trainerDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.update(trainerDto);
        return backToUpdateRegisteredProgram(trainerDto.getRegisteredProgramId(), model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/{registeredProgramId}/trainer/{id}/delete")
    public String deleteTrainer(@PathVariable("id") Long id,
                                 @PathVariable("registeredProgramId") Long registeredProgramId, Model model) {
        registrationRequirementsCrudService.delete(id);
        return backToUpdateRegisteredProgram(registeredProgramId, model);
    }
}
