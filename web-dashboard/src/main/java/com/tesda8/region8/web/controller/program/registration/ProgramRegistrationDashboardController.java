package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.model.wrapper.RegisteredProgramRequest;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.RegisteredProgramService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.InstitutionType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.web.controller.HeaderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class ProgramRegistrationDashboardController extends HeaderController {

    private static Logger logger = LoggerFactory.getLogger(ProgramRegistrationDashboardController.class);
    private static final String ALL = "ALL";

    private RegisteredProgramService registeredProgramService;
    private InstitutionService institutionService;

    @Autowired
    public ProgramRegistrationDashboardController(RegisteredProgramService registeredProgramService,
                                                  InstitutionService institutionService,
                                                  RegisteredProgramStatusService registeredProgramStatusService,
                                                  ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.registeredProgramService = registeredProgramService;
        this.institutionService = institutionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dashboard/registeredPrograms")
    public String showInstitutionsProgRegCount(Model model) {
        ProgramRegistrationWrapper programRegistrationWrapper = registeredProgramService.getCourseCountPerInstitution();
        model.addAttribute("institutionsList", programRegistrationWrapper);
        addStatusCounterToModel(model);
        return "dashboard/prog_reg_dashboard";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dashboard/registeredPrograms/{sectorType}/sector")
    public String getRegisteredProgramPerSector(@PathVariable("sectorType") Sector sector, Model model) {
        logger.info("Sector : {}", sector);
        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramService.getAllRegisteredProgramsByCourseSectorAndInstitutionClassification(sector, InstitutionClassification.TESDA);
        List<InstitutionDto> ttiList = institutionService.getAllInstitutionByInstitutionTypeAndInstitutionClassification(InstitutionType.PUBLIC, InstitutionClassification.TESDA);
        model.addAttribute("sectorValue", sector);
        model.addAttribute("registeredProgramRequest", new RegisteredProgramRequest());
        model.addAttribute("registeredPrograms", registeredProgramDtoList);
        model.addAttribute("ttiList", ttiList);
        addStatusCounterToModel(model);
        return "dashboard/prog_reg_list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/dashboard/registeredPrograms/search")
    public String getRegisteredProgramByFilter(@ModelAttribute RegisteredProgramRequest registeredProgramRequest,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        // handles bootstrap select bug not including ALL option
        if (registeredProgramRequest.getInstitutionNames().length == 0) {
            registeredProgramRequest.setInstitutionNames(new String[] {ALL});
        }
        List<RegisteredProgramDto> registeredProgramDtoList =
                registeredProgramService.getAllRegisteredProgramsByNameAndSectorAndCourseName(registeredProgramRequest.getInstitutionNames(),
                        registeredProgramRequest.getSector(), registeredProgramRequest.getCourseName());
        List<InstitutionDto> ttiList = institutionService.getAllInstitutionByInstitutionTypeAndInstitutionClassification(InstitutionType.PUBLIC, InstitutionClassification.TESDA);
        model.addAttribute("sectorValue", registeredProgramRequest.getSector());
        model.addAttribute("courseNameValue", registeredProgramRequest.getCourseName());
        model.addAttribute("registeredProgramRequest", registeredProgramRequest);
        model.addAttribute("registeredPrograms", registeredProgramDtoList);
        model.addAttribute("ttiList", ttiList);
        addStatusCounterToModel(model);
        return "dashboard/prog_reg_list";
    }

}
