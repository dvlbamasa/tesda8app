package com.tesda8.region8.web.controller.certification;

import com.tesda8.region8.program.registration.model.dto.CertificateDto;
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
public class CertificateController extends DefaultController {

    private RegistrationRequirementsCrudService registrationRequirementsCrudService;

    @Autowired
    public CertificateController(@Qualifier("certificate") RegistrationRequirementsCrudService registrationRequirementsCrudService,
                                 RegisteredProgramStatusService registeredProgramStatusService) {
        super(registeredProgramStatusService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/certification/{id}/certificate/create")
    public String createNewCertificate(@PathVariable("id") Long id, Model model) {
        CertificateDto certificateDto = new CertificateDto();
        certificateDto.setTrainerId(id);
        model.addAttribute("certificate", certificateDto);
        addStatusCounterToModel(model);
        return "certification/add_certificate";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/{id}/certificate/update")
    public String updateCertificate(@PathVariable("id") Long id, Model model) {
        CertificateDto certificateDto = (CertificateDto) registrationRequirementsCrudService.get(id);
        model.addAttribute("certificate", certificateDto);
        addStatusCounterToModel(model);
        return "certification/update_certificate";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certification/certificate/save")
    public String saveNewCertificate(@ModelAttribute CertificateDto certificateDto, BindingResult bindingResult,
                                     Model model) {
        registrationRequirementsCrudService.create(certificateDto);
        return "redirect:/certification/trainer/" + certificateDto.getTrainerId() + "/details";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certification/certificate/update")
    public String updateCertificate(@ModelAttribute CertificateDto certificateDto, BindingResult bindingResult,
                                    Model model) {
        registrationRequirementsCrudService.update(certificateDto);
        return "redirect:/certification/trainer/" + certificateDto.getTrainerId() + "/details";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/{trainerId}/trainer/{id}/certificate/delete")
    public String deleteCertificate(@PathVariable("trainerId") Long trainerId, @PathVariable("id") Long id,
                                    Model model) {
        registrationRequirementsCrudService.delete(id);
        return "redirect:/certification/trainer/" + trainerId + "/details";
    }

}
