package com.tesda8.region8.web.controller.planning;

import com.google.common.collect.Lists;
import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.planning.model.dto.OperatingUnitDataDto;
import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.wrapper.PapDataFilterRequest;
import com.tesda8.region8.planning.model.wrapper.PapDataWrapper;
import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.OperatingUnitPOType;
import com.tesda8.region8.util.enums.PapGroupType;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.HeaderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PlanningController extends HeaderController {


    private PapDataService papDataService;
    private final Long CURRENT_YEAR = ApplicationUtil.getCurrentYear();
    private final Month CURRENT_MONTH = ApplicationUtil.getCurrentMonth();

    @Autowired
    public PlanningController(PapDataService papDataService, RegisteredProgramStatusService registeredProgramStatusService,
                              ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.papDataService = papDataService;
    }

    @GetMapping("/planning")
    public String showPlanning(Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        if (ApplicationUtil.getSuccessIndicatorType(roleName).equals("PO")) {
            setModelInitialAtributes(model, roleName, "PO");
            return "planning/planning";
        } else {
            setModelInitialAtributes(model, roleName, "TTI");
            return "planning/planning_tti";
        }
    }

    @GetMapping("/planning/tti")
    public String showPlanningTTI(Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        setModelInitialAtributes(model, roleName, "TTI");
        return "planning/planning_tti";

    }

    @GetMapping("/planning/opcr/update")
    public String updateOpcr(Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        if (ApplicationUtil.getSuccessIndicatorType(roleName).equals("PO")) {
            setModelInitialAtributes(model, roleName, "PO");
            return "planning/update_opcr";
        } else {
            setModelInitialAtributes(model, roleName, "TTI");
            return "planning/update_opcr_tti";
        }
    }

    @GetMapping("/planning/tti/opcr/update")
    public String updateTTIOpcr(Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        setModelInitialAtributes(model, roleName, "TTI");
        return "planning/update_opcr_tti";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/planning/opcr/filter",
            consumes = "application/x-www-form-urlencoded")
    public String filterOpcr(PapDataFilterRequest papDataFilterRequest,
                             BindingResult bindingResult, Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());

        if (ApplicationUtil.getSuccessIndicatorType(roleName).equals("PO")) {
            setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, "PO");
            return "planning/planning";
        } else {
            setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, "TTI");
            return "planning/planning_tti";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/opcr/update/filter",
            consumes = "application/x-www-form-urlencoded")
    public String updateOpcrWithFilter(PapDataFilterRequest papDataFilterRequest,
                                       BindingResult bindingResult, Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        if (ApplicationUtil.getSuccessIndicatorType(roleName).equals("PO")) {
            setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, "PO");
            return "planning/update_opcr";
        } else {
            setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, "TTI");
            return "planning/update_opcr_tti";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/tti/opcr/filter",
            consumes = "application/x-www-form-urlencoded")
    public String filterTTIOpcr(PapDataFilterRequest papDataFilterRequest,
                             BindingResult bindingResult, Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, "TTI");
        return "planning/planning_tti";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/tti/opcr/update/filter",
            consumes = "application/x-www-form-urlencoded")
    public String updateTTIOpcrWithFilter(PapDataFilterRequest papDataFilterRequest,
                                       BindingResult bindingResult, Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, "TTI");
        return "planning/update_opcr_tti";
    }

    @GetMapping("/planning/successIndicator/update")
    public String updateSuccessIndicator(@RequestParam(value = "updateType", required = false) String updateType, Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        setModelInitialAtributes(model, roleName, updateType);
        return "planning/update_success_indicator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/successIndicator/update/filter",
            consumes = "application/x-www-form-urlencoded")
    public String updateSuccessIndicatorWithFilter(@RequestParam("updateType") String updateType,
                                                   PapDataFilterRequest papDataFilterRequest,
                                                   BindingResult bindingResult, Model model, Authentication authentication) {
        String roleName = ApplicationUtil.trimRoleName(authentication.getAuthorities().toString());
        setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model, roleName, updateType);
        return "planning/update_success_indicator";
    }

    @GetMapping("/planning/successIndicator/create")
    public String createSuccessIndicator(@RequestParam(value = "year", required = false) Long year, Model model) {
        model.addAttribute("successIndicator", initializeSuccessIndicatorDto());
        model.addAttribute("papDataList", papDataService.getAllPapDataByYear(Optional.ofNullable(year).orElse(CURRENT_YEAR)));
        model.addAttribute("papFilter", new PapDataFilterRequest(Optional.ofNullable(year).orElse(CURRENT_YEAR)));
        addStatusCounterToModel(model);
        return "planning/create_success_indicator";
    }

    @GetMapping("/planning/pap/manage")
    public String managePap(@RequestParam(value = "year", required = false) Long year, Model model) {
        model.addAttribute("papData", new PapDataDto());
        model.addAttribute("papDataList", papDataService.getAllPapDataByYear(Optional.ofNullable(year).orElse(CURRENT_YEAR)));
        model.addAttribute("papFilter", new PapDataFilterRequest(Optional.ofNullable(year).orElse(CURRENT_YEAR)));
        addStatusCounterToModel(model);
        return "planning/manage_pap";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/successIndicator/create/filter",
            consumes = "application/x-www-form-urlencoded")
    public String createSuccessIndicatorWithFilter(PapDataFilterRequest papDataFilterRequest,
                                                    BindingResult bindingResult, Model model) {
        model.addAttribute("successIndicator", initializeSuccessIndicatorDto());
        model.addAttribute("papDataList", papDataService.getAllPapDataByYear(papDataFilterRequest.getYear()));
        model.addAttribute("papFilter", papDataFilterRequest);
        addStatusCounterToModel(model);
        return "planning/create_success_indicator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/pap/manage/filter",
            consumes = "application/x-www-form-urlencoded")
    public String managePapWithFilter(PapDataFilterRequest papDataFilterRequest,
                                      BindingResult bindingResult, Model model) {
        model.addAttribute("papData", new PapDataDto());
        model.addAttribute("papDataList", papDataService.getAllPapDataByYear(papDataFilterRequest.getYear()));
        model.addAttribute("papFilter", papDataFilterRequest);
        addStatusCounterToModel(model);
        return "planning/manage_pap";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/successIndicators/create/save",
            consumes = "application/x-www-form-urlencoded")
    public String saveNewSuccessIndicator(SuccessIndicatorDataDto successIndicatorDataDto,
                                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        papDataService.createSuccessIndicator(successIndicatorDataDto);
        return "redirect:/planning";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/papData/create/save",
            consumes = "application/x-www-form-urlencoded")
    public String saveNewPapData(@RequestParam("year") Long year, PapDataDto papDataDto,
                                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        papDataService.createPapData(papDataDto, year);
        model.addAttribute("successIndicator", initializeSuccessIndicatorDto());
        model.addAttribute("papDataList", papDataService.getAllPapDataByYear(year));
        model.addAttribute("papFilter", new PapDataFilterRequest(year));
        addStatusCounterToModel(model);
        return "planning/create_success_indicator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/papData/delete",
            consumes = "application/x-www-form-urlencoded")
    public String deletePapData(@RequestParam("year") Long year, PapDataDto papDataDto,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        papDataService.deletePapData(papDataDto);
        model.addAttribute("successIndicator", initializeSuccessIndicatorDto());
        model.addAttribute("papDataList", papDataService.getAllPapDataByYear(year));
        model.addAttribute("papFilter", new PapDataFilterRequest(year));
        addStatusCounterToModel(model);
        return "planning/create_success_indicator";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/planning/graph/{papGroup}/papGroup")
    public String generateGraph(@PathVariable("papGroup") PapGroupType papGroupType,
                                @RequestParam("papName") String papName,
                                @RequestParam("measure") String measure,
                                @RequestParam("year") Long year,
                                @RequestParam("month") Month month,
                                @RequestParam("pageType") String pageType,
                                Model model) {
        List<SuccessIndicatorDataDto> successIndicatorDataDtoList = Lists.newArrayList();
        switch (papGroupType) {
            case TESDPP:
                successIndicatorDataDtoList = papDataService.getAllSuccessIndicatorsByFilter(PapGroupType.TESDPP, measure, papName, Optional.ofNullable(year).orElse(CURRENT_YEAR), month, pageType);
                break;
            case TESDRP:
                successIndicatorDataDtoList = papDataService.getAllSuccessIndicatorsByFilter(PapGroupType.TESDRP, measure, papName, Optional.ofNullable(year).orElse(CURRENT_YEAR), month, pageType);
                break;
            case TESDP:
                successIndicatorDataDtoList = papDataService.getAllSuccessIndicatorsByFilter(PapGroupType.TESDP, measure, papName, Optional.ofNullable(year).orElse(CURRENT_YEAR), month, pageType);
                break;
            case STO:
                successIndicatorDataDtoList = papDataService.getAllSuccessIndicatorsByFilter(PapGroupType.STO, measure, papName, Optional.ofNullable(year).orElse(CURRENT_YEAR), month, pageType);
                break;
            case GASS:
                successIndicatorDataDtoList = papDataService.getAllSuccessIndicatorsByFilter(PapGroupType.GASS, measure, papName, Optional.ofNullable(year).orElse(CURRENT_YEAR), month, pageType);
                break;
            default:
                break;
        }
        model.addAttribute("papName", papName);
        model.addAttribute("measure", measure);
        model.addAttribute("papGroupType", papGroupType);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("pageType", pageType);
        model.addAttribute("successIndicatorDataDtoList", successIndicatorDataDtoList);
        addStatusCounterToModel(model);
        return "planning/opcr_graph";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/planning/successIndicators/{papGroup}/papGroup/save",
            consumes = "application/x-www-form-urlencoded")
    public String saveSuccessIndicators(@PathVariable("papGroup") PapGroupType papGroupType,
                           PapDataWrapper papDataWrapper,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        switch (papGroupType) {
            case TESDPP:
                papDataService.updateSuccessIndicators(papDataWrapper.getTesdppData());
                break;
            case TESDRP:
                papDataService.updateSuccessIndicators(papDataWrapper.getTesdrpData());
                break;
            case TESDP:
                papDataService.updateSuccessIndicators(papDataWrapper.getTesdpData());
                break;
            case STO:
                papDataService.updateSuccessIndicators(papDataWrapper.getStoData());
                break;
            case GASS:
                papDataService.updateSuccessIndicators(papDataWrapper.getGassData());
                break;
            default:
                break;
        }
        return "redirect:/planning";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/opcr/{papGroup}/papGroup/save",
            consumes = "application/x-www-form-urlencoded")
    public String saveOpcr(@RequestParam("updateType") String updateType,
                            @RequestParam("month") Month month,
                            @PathVariable("papGroup") PapGroupType papGroupType,
                            PapDataWrapper papDataWrapper,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        switch (papGroupType) {
            case TESDPP:
                papDataService.updatePapData(papDataWrapper.getTesdppData(), updateType, month);
                break;
            case TESDRP:
                papDataService.updatePapData(papDataWrapper.getTesdrpData(), updateType, month);
                break;
            case TESDP:
                papDataService.updatePapData(papDataWrapper.getTesdpData(), updateType, month);
                break;
            case STO:
                papDataService.updatePapData(papDataWrapper.getStoData(), updateType, month);
                break;
            case GASS:
                papDataService.updatePapData(papDataWrapper.getGassData(), updateType, month);
                break;
            default:
                break;
        }
        return "redirect:/planning";
    }

    private void setModelInitialAtributes(Model model, String role, String pageType) {
        PapDataWrapper papDataWrapper = papDataService.getAllPapDataWrapperByFilter("", "", CURRENT_YEAR, CURRENT_MONTH, role, pageType);
        model.addAttribute("papFilter", new PapDataFilterRequest(CURRENT_YEAR, CURRENT_MONTH));
        model.addAttribute("papData", papDataWrapper);
        addStatusCounterToModel(model);
    }

    private void setModelAttributesWithFilter(PapDataFilterRequest papDataFilterRequest, BindingResult bindingResult, Model model, String role, String pageType) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        PapDataWrapper papDataWrapper = papDataService.getAllPapDataWrapperByFilter(papDataFilterRequest.getSuccessIndicatorMeasure(), papDataFilterRequest.getPapName(), papDataFilterRequest.getYear(), papDataFilterRequest.getMonth(), role, pageType);
        model.addAttribute("papFilter", papDataFilterRequest);
        model.addAttribute("papData", papDataWrapper);
        addStatusCounterToModel(model);
    }

    private SuccessIndicatorDataDto initializeSuccessIndicatorDto() {
        SuccessIndicatorDataDto successIndicatorDataDto = new SuccessIndicatorDataDto();
        successIndicatorDataDto.setIsAccumulated(true);
        successIndicatorDataDto.setIsPercentage(false);
        successIndicatorDataDto.setIsDeleted(false);
        successIndicatorDataDto.setOperatingUnitDataList(Lists.newArrayList());
        Arrays.asList(OperatingUnitPOType.values()).forEach(
                operatingUnitPOType -> {
                    OperatingUnitDataDto operatingUnitDataDto = new OperatingUnitDataDto();
                    operatingUnitDataDto.setOperatingUnitType(operatingUnitPOType);
                    operatingUnitDataDto.setTarget(0);
                    operatingUnitDataDto.setOutput(0);
                    operatingUnitDataDto.setRate(0);
                    successIndicatorDataDto.getOperatingUnitDataList().add(operatingUnitDataDto);
                }
        );
        return successIndicatorDataDto;
    }
}
