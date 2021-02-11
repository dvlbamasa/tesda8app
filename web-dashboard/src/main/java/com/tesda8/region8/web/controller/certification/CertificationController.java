package com.tesda8.region8.web.controller.certification;

import com.tesda8.region8.program.registration.model.dto.CertificateDto;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.dto.TrainerFilter;
import com.tesda8.region8.program.registration.model.entities.Certificate;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.program.registration.service.TrainerService;
import com.tesda8.region8.web.controller.DefaultController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class CertificationController extends DefaultController {

    private TrainerService trainerService;
    private RegistrationRequirementsCrudService registrationRequirementsCrudService;

    @Autowired
    public CertificationController(TrainerService trainerService,
                                   @Qualifier("trainer") RegistrationRequirementsCrudService registrationRequirementsCrudService,
                                   RegisteredProgramStatusService registeredProgramStatusService) {
        super(registeredProgramStatusService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
        this.trainerService = trainerService;
    }

    @GetMapping("/certification")
    public String certification(Model model) {
        model.addAttribute("trainers", trainerService.getAllTrainerByFilter(new TrainerFilter()));
        model.addAttribute("trainerFilter", new TrainerFilter());
        addStatusCounterToModel(model);
        return "certification/certification";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certification/search")
    public String getTvetTrainersByFilter(@ModelAttribute TrainerFilter trainerFilter, BindingResult bindingResult,
                                          Model model) {
        model.addAttribute("trainers", trainerService.getAllTrainerByFilter(trainerFilter));
        model.addAttribute("trainerFilter", trainerFilter);
        addStatusCounterToModel(model);
        return "certification/certification";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/trainer/{id}/details")
    public String getTvetTrainer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("trainer", (TrainerDto) registrationRequirementsCrudService.get(id));
        addStatusCounterToModel(model);
        return "certification/tvet_trainer_details";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/trainer/create")
    public String createTvetTrainer(Model model) {
        model.addAttribute("trainer", new TrainerDto());
        addStatusCounterToModel(model);
        return "certification/add_tvet_trainer";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certification/trainer/create/save")
    public String saveTvetTrainer(@ModelAttribute TrainerDto trainerDto, BindingResult bindingResult,
                                  Model model) {
        TrainerDto savedTrainerDto = trainerService.createTrainer(trainerDto);
        return "redirect:/certification/" + savedTrainerDto.getId() + "/certificate/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certification/trainer/update/save")
    public String updateTvetTrainer(@ModelAttribute TrainerDto trainerDto, BindingResult bindingResult,
                                    Model model) {
        registrationRequirementsCrudService.update(trainerDto);
        return "redirect:/certification";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/trainer/{id}/delete")
    public String deleteTrainer(@PathVariable("id") Long id, Model model) {
        registrationRequirementsCrudService.delete(id);
        return "redirect:/certification";
    }
}