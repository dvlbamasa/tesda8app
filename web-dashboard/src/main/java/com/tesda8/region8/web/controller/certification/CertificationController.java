package com.tesda8.region8.web.controller.certification;

import com.tesda8.region8.certification.model.dto.ExpiredCertificateFilter;
import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.certification.service.RegistryTrainerExcelService;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.dto.TrainerFilter;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.program.registration.service.RegistrationRequirementsCrudService;
import com.tesda8.region8.program.registration.service.TrainerService;
import com.tesda8.region8.util.enums.EducationalAttainment;
import com.tesda8.region8.util.enums.ExpiredCertificateType;
import com.tesda8.region8.util.enums.Sex;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.HeaderController;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class CertificationController extends HeaderController {

    private TrainerService trainerService;
    private RegistrationRequirementsCrudService registrationRequirementsCrudService;
    private RegistryTrainerExcelService registryTrainerExcelService;

    @Autowired
    public CertificationController(TrainerService trainerService,
                                   @Qualifier("trainer") RegistrationRequirementsCrudService registrationRequirementsCrudService,
                                   RegisteredProgramStatusService registeredProgramStatusService,
                                   RegistryTrainerExcelService registryTrainerExcelService,
                                   ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.registrationRequirementsCrudService = registrationRequirementsCrudService;
        this.trainerService = trainerService;
        this.registryTrainerExcelService = registryTrainerExcelService;
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

    @RequestMapping(method = RequestMethod.GET, value = "/certification/registry/download")
    public void downloadRegistry(HttpServletResponse httpServletResponse,
                                 @RequestParam("fullName") String fullName,
                                 @RequestParam("sex") Sex sex,
                                 @RequestParam("address") String address,
                                 @RequestParam("contactNumber") String contactNumber,
                                 @RequestParam("emailAddress") String emailAddress,
                                 @RequestParam("educationalAttainment")EducationalAttainment educationalAttainment) throws IOException {
        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = dateFormatter.format(ApplicationUtil.convertToDateViaInstant(ApplicationUtil.getLocalDateTimeNow()));
        String headerValue = "attachment; filename=Registry of TVET Trainers as of " + dateNow + ".xlsx";
        httpServletResponse.setHeader(headerKey, headerValue);
        registryTrainerExcelService.parseRegistryReport(httpServletResponse, new TrainerFilter(fullName, sex, address, contactNumber, emailAddress, educationalAttainment));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/expired")
    public String getExpiredCertificates(@RequestParam(value = "trainerName", required = false) String trainerName,
                                         Model model){
        addStatusCounterToModel(model);
        addExpiredCertificateListToModel(model, ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), trainerName, ExpiredCertificateType.ALL);
        model.addAttribute("expiredCertificateFilter", new ExpiredCertificateFilter(trainerName, ExpiredCertificateType.ALL));
        return "certification/expired_certificates";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/certification/expired/search")
    public String searchExpiredCertificates(@ModelAttribute ExpiredCertificateFilter expiredCertificateFilter,
                                            BindingResult bindingResult,
                                            Model model) {
        addStatusCounterToModel(model);
        addExpiredCertificateListToModel(model, ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), expiredCertificateFilter.getTrainerName(), expiredCertificateFilter.getExpiredCertificateType());
        model.addAttribute("expiredCertificateFilter", expiredCertificateFilter);
        return "certification/expired_certificates";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/certification/expired/pagination")
    public String expiredCertificatesPagination(Model model,
                                                @RequestParam("trainerName") String trainerName,
                                                @RequestParam("expiredCertificateType") ExpiredCertificateType expiredCertificateType,
                                                @RequestParam("pageNumber") int pageNumber) {
        ExpiredCertificateFilter expiredCertificateFilter = new ExpiredCertificateFilter(trainerName, expiredCertificateType);
        addStatusCounterToModel(model);
        addExpiredCertificateListToModel(model, pageNumber, ApplicationUtil.getDefaultPageSize(), trainerName, expiredCertificateType);
        model.addAttribute("expiredCertificateFilter", expiredCertificateFilter);
        return "certification/expired_certificates";
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

    @RequestMapping(method = RequestMethod.GET, value = "/certification/trainer/{trainerId}/certificate/{certificateId}")
    public String fetchCertificateLayoutData(@PathVariable("trainerId") Long trainerId,
                                             @PathVariable("certificateId") Long certificateId,
                                             Model model) {
        addStatusCounterToModel(model);
        model.addAttribute("certificateData", trainerService.fetchCertificateLayout(trainerId, certificateId));
        return "certification/certificate_layout";
    }
}
