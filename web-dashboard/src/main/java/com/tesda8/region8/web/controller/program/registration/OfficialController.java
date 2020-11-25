package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.OfficialDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class OfficialController extends RequirementControllerUtil {

    private RegistrationRequirementsCrudService<OfficialDto> registrationRequirementsCrudService;

    private static Logger logger = LoggerFactory.getLogger(OfficialController.class);

    @Autowired
    public OfficialController(@Qualifier("official") RegistrationRequirementsCrudService registrationRequirementsCrudService,
                              InstitutionService institutionService) {
        super(institutionService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/registeredProgram/{id}/official/create")
    public String createOfficial(@PathVariable("id") Long id, Model model) {
        OfficialDto officialDto = new OfficialDto();
        officialDto.setRegisteredProgramId(id);
        model.addAttribute("official", officialDto);
        return "program_registration/official/add_official";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/official/{id}/update")
    public String updateOfficial(@PathVariable("id") Long id, Model model) {
        OfficialDto officialDto = registrationRequirementsCrudService.get(id);
        model.addAttribute("official", officialDto);
        return "program_registration/official/update_official";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/official/create/save")
    public String createOfficialSave(@ModelAttribute OfficialDto officialDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.create(officialDto);
        return backToUpdateRegisteredProgram(officialDto.getRegisteredProgramId(), model);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/official/update/save")
    public String updateOfficialSave(@ModelAttribute OfficialDto officialDto, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        registrationRequirementsCrudService.update(officialDto);
        return backToUpdateRegisteredProgram(officialDto.getRegisteredProgramId(), model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/{registeredProgramId}/official/{id}/delete")
    public String deleteOfficial(@PathVariable("id") Long id,
                                 @PathVariable("registeredProgramId") Long registeredProgramId, Model model) {
        registrationRequirementsCrudService.delete(id);
        return backToUpdateRegisteredProgram(registeredProgramId, model);
    }

}
