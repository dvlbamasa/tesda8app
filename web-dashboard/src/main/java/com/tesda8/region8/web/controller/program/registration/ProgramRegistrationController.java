package com.tesda8.region8.web.controller.program.registration;

import com.google.common.collect.Lists;
import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.model.dto.ExpiredDocumentFilter;
import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.NonTeachingStaffDto;
import com.tesda8.region8.program.registration.model.dto.OfficialDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.dto.TrainerDto;
import com.tesda8.region8.program.registration.model.entities.RegisteredProgram;
import com.tesda8.region8.program.registration.service.CompendiumExcelService;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.RegisteredProgramService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.CourseStatus;
import com.tesda8.region8.util.enums.ExpiredDocumentType;
import com.tesda8.region8.util.enums.InstitutionClassification;
import com.tesda8.region8.util.enums.OperatingUnitType;
import com.tesda8.region8.util.enums.Sector;
import com.tesda8.region8.util.enums.SortOrder;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.HeaderController;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProgramRegistrationController extends HeaderController {

    private InstitutionService institutionService;
    private RegisteredProgramService registeredProgramService;
    private CompendiumExcelService compendiumExcelService;
    private static Logger logger = LoggerFactory.getLogger(ProgramRegistrationController.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");

    @Autowired
    public ProgramRegistrationController(InstitutionService institutionService,
                                         RegisteredProgramService registeredProgramService,
                                         RegisteredProgramStatusService registeredProgramStatusService,
                                         CompendiumExcelService compendiumExcelService,
                                         ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.institutionService = institutionService;
        this.registeredProgramService = registeredProgramService;
        this.compendiumExcelService = compendiumExcelService;
    }

    @GetMapping("/program_registration")
    public String programRegistration(Model model) {
        initializeModel(model);
        return "program_registration/program_registration";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/registeredProgram/search")
    public String registeredProgramsWithFilter(@ModelAttribute RegisteredProgramFilter registeredProgramFilter,
                                               BindingResult bindingResult,
                                               Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        List<RegisteredProgramDto> registeredProgramDtoList = registeredProgramService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
        List<InstitutionDto> institutionDtoList = institutionService.getAllInstitution();
        model.addAttribute("registeredProgramFilter", registeredProgramFilter);
        model.addAttribute("registeredPrograms", registeredProgramDtoList);
        model.addAttribute("ttiList", institutionDtoList);
        model.addAttribute("total", registeredProgramDtoList.size());
        addStatusCounterToModel(model);
        addExpiredDocumentsListToModel(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), ExpiredDocumentType.ALL, model);
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

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/documents/expired")
    public String expiredDocuments(Model model) {
        addExpiredDocumentsListToModel(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), ExpiredDocumentType.ALL, model);
        model.addAttribute("filter", new ExpiredDocumentFilter(ExpiredDocumentType.ALL));
        addStatusCounterToModel(model);
        return "program_registration/documents_expired";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/program_registration/documents/expired/search")
    public String expiredDocumentsSearch(@ModelAttribute ExpiredDocumentFilter expiredDocumentFilter, Model model) {
        addExpiredDocumentsListToModel(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), expiredDocumentFilter.getExpiredDocumentType(), model);
        model.addAttribute("filter", expiredDocumentFilter);
        addStatusCounterToModel(model);
        return "program_registration/documents_expired";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/documents/expired/pagination")
    public String expiredDocumentsPagination(Model model,
                                             @RequestParam("expiredDocumentType") ExpiredDocumentType expiredDocumentType,
                                             @RequestParam("pageNumber") int pageNumber) {
        addStatusCounterToModel(model);
        model.addAttribute("filter", new ExpiredDocumentFilter(expiredDocumentType));
        addExpiredDocumentsListToModel(pageNumber, ApplicationUtil.getDefaultPageSize(), expiredDocumentType, model);
        return "program_registration/documents_expired";
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
        addExpiredDocumentsListToModel(ApplicationUtil.getDefaultPageNumber(), ApplicationUtil.getDefaultPageSize(), ExpiredDocumentType.ALL, model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/program_registration/compendium/download")
    public void downloadCompendium(HttpServletResponse httpServletResponse,
                                   @RequestParam("institutionIds") Long [] institutionIds,
                                   @RequestParam("sector") Sector sector,
                                   @RequestParam("courseName") String courseName,
                                   @RequestParam("operatingUnitType") OperatingUnitType [] operatingUnitType,
                                   @RequestParam("institutionClassification") InstitutionClassification [] institutionClassification,
                                   @RequestParam("registeredProgramNumber") String registeredProgramNumber,
                                   @RequestParam("dateIssuedFrom") String dateIssuedFrom,
                                   @RequestParam("dateIssuedTo") String dateIssuedTo,
                                   @RequestParam("courseStatus") CourseStatus courseStatus,
                                   @RequestParam("isClosed") Boolean isClosed,
                                   @RequestParam("sortOrder") SortOrder sortOrder) throws IOException, ParseException {
        Date from = dateIssuedFrom.equals("") ? null : SIMPLE_DATE_FORMAT.parse(dateIssuedFrom);
        Date to = dateIssuedTo.equals("") ? null : SIMPLE_DATE_FORMAT.parse(dateIssuedTo);
        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = dateFormatter.format(ApplicationUtil.convertToDateViaInstant(ApplicationUtil.getLocalDateTimeNow()));
        String headerValue = "attachment; filename=Compendium as of " + dateNow + ".xlsx";
        httpServletResponse.setHeader(headerKey, headerValue);
        compendiumExcelService.parseCompendium(httpServletResponse, new RegisteredProgramFilter(institutionIds, sector, courseName,operatingUnitType, institutionClassification, registeredProgramNumber,from, to,courseStatus, isClosed, sortOrder));
    }
}
