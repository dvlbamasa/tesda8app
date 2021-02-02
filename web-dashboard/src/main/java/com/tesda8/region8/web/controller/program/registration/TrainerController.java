package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.program.registration.service.TrainerService;
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

import java.util.List;

@Controller
public class TrainerController extends DefaultController {

    private RegistrationRequirementsCrudService<TrainerDto> registrationRequirementsCrudService;
    private TrainerService trainerService;

    @Autowired
    public TrainerController(@Qualifier("trainer") RegistrationRequirementsCrudService registrationRequirementsCrudService,
                             RegisteredProgramStatusService registeredProgramStatusService,
                             TrainerService trainerService) {
        super(registeredProgramStatusService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
        this.trainerService = trainerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/trainer/create")
    public String createTrainer(@PathVariable("id") Long id, Model model) {
        TrainerDto trainerDto = new TrainerDto();
        List<TrainerDto> trainerDtoList = trainerService.getAllTrainer();
        trainerDto.setRegisteredProgramId(id);
        model.addAttribute("trainerList", trainerDtoList);
        model.addAttribute("trainer", trainerDto);
        addStatusCounterToModel(model);
        return "program_registration/trainer/add_trainer";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/trainer/{id}/update")
    public String updateTrainer(@PathVariable("id") Long id, Model model) {
        TrainerDto trainerDto = registrationRequirementsCrudService.get(id);
        model.addAttribute("trainer", trainerDto);
        addStatusCounterToModel(model);
        return "program_registration/trainer/update_trainer";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/trainer/create/save")
    public String createTrainerSave(@ModelAttribute TrainerDto trainerDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.create(trainerDto);
        return "redirect:/program_registration/registeredProgram/" + trainerDto.getRegisteredProgramId() + "/update";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/trainer/update/save")
    public String updateTrainerSave(@ModelAttribute TrainerDto trainerDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        trainerService.updateLinkedTrainer(trainerDto);
        return "redirect:/program_registration/registeredProgram/" + trainerDto.getRegisteredProgramId() + "/update";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/{registeredProgramId}/trainer/{id}/delete")
    public String deleteTrainer(@PathVariable("id") Long id,
                                 @PathVariable("registeredProgramId") Long registeredProgramId, Model model) {
        trainerService.unlinkTrainer(id);
        return "redirect:/program_registration/registeredProgram/" + registeredProgramId + "/update";
    }
}
