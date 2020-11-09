package com.tesda8.region8.web.controller.planning;

import com.tesda8.region8.planning.model.wrapper.PapDataFilterRequest;
import com.tesda8.region8.planning.model.wrapper.PapDataWrapper;
import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.util.enums.PapGroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PlanningController {

    private PapDataService papDataService;

    @Autowired
    public PlanningController(PapDataService papDataService) {
        this.papDataService = papDataService;
    }

    @GetMapping("/planning")
    public String showPlanning(Model model) {
        setModelInitialAtributes(model);
        return "planning/planning";
    }

    @GetMapping("/planning/opcr/update")
    public String updateOpcr(Model model) {
        setModelInitialAtributes(model);
        return "planning/update_opcr";
    }

    @GetMapping("/planning/successIndicator/update")
    public String updateSuccessIndicator(Model model) {
        setModelInitialAtributes(model);
        return "planning/update_success_indicator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/opcr/update/filter",
            consumes = "application/x-www-form-urlencoded")
    public String updateOpcrWithFilter(PapDataFilterRequest papDataFilterRequest,
                             BindingResult bindingResult, Model model) {
        setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model);
        return "planning/update_opcr";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/successIndicator/update/filter",
            consumes = "application/x-www-form-urlencoded")
    public String updateSuccessIndicatorWithFilter(PapDataFilterRequest papDataFilterRequest,
                                       BindingResult bindingResult, Model model) {
        setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model);
        return "planning/update_success_indicator";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/opcr/filter",
            consumes = "application/x-www-form-urlencoded")
    public String filterOpcr(PapDataFilterRequest papDataFilterRequest,
                             BindingResult bindingResult, Model model) {
        setModelAttributesWithFilter(papDataFilterRequest, bindingResult, model);
        return "planning/planning";
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
        setModelInitialAtributes(model);
        return "planning/planning";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/planning/opcr/{papGroup}/papGroup/save",
            consumes = "application/x-www-form-urlencoded")
    public String saveOpcr(@PathVariable("papGroup") PapGroupType papGroupType,
                            PapDataWrapper papDataWrapper,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        switch (papGroupType) {
            case TESDPP:
                papDataService.updatePapData(papDataWrapper.getTesdppData());
                break;
            case TESDRP:
                papDataService.updatePapData(papDataWrapper.getTesdrpData());
                break;
            case TESDP:
                papDataService.updatePapData(papDataWrapper.getTesdpData());
                break;
            case STO:
                papDataService.updatePapData(papDataWrapper.getStoData());
                break;
            case GASS:
                papDataService.updatePapData(papDataWrapper.getGassData());
                break;
            default:
                break;
        }
        setModelInitialAtributes(model);
        return "planning/planning";
    }

    private void setModelInitialAtributes(Model model) {
        PapDataWrapper papDataWrapper2 = new PapDataWrapper();
        papDataWrapper2.setTesdppData(papDataService.getAllPapDataByPapGroupType(PapGroupType.TESDPP));
        papDataWrapper2.setTesdrpData(papDataService.getAllPapDataByPapGroupType(PapGroupType.TESDRP));
        papDataWrapper2.setTesdpData(papDataService.getAllPapDataByPapGroupType(PapGroupType.TESDP));
        papDataWrapper2.setStoData(papDataService.getAllPapDataByPapGroupType(PapGroupType.STO));
        papDataWrapper2.setGassData(papDataService.getAllPapDataByPapGroupType(PapGroupType.GASS));
        model.addAttribute("papFilter", new PapDataFilterRequest());
        model.addAttribute("papNameValue", "");
        model.addAttribute("successIndicatorMeasureValue", "");
        model.addAttribute("papData", papDataWrapper2);
    }

    private void setModelAttributesWithFilter(PapDataFilterRequest papDataFilterRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        PapDataWrapper papDataWrapper = new PapDataWrapper();
        papDataWrapper.setTesdppData(papDataService.getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType.TESDPP,
                papDataFilterRequest.getSuccessIndicatorMeasure(), papDataFilterRequest.getPapName()));
        papDataWrapper.setTesdrpData(papDataService.getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType.TESDRP,
                papDataFilterRequest.getSuccessIndicatorMeasure(), papDataFilterRequest.getPapName()));
        papDataWrapper.setTesdpData(papDataService.getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType.TESDP,
                papDataFilterRequest.getSuccessIndicatorMeasure(), papDataFilterRequest.getPapName()));
        papDataWrapper.setStoData(papDataService.getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType.STO,
                papDataFilterRequest.getSuccessIndicatorMeasure(), papDataFilterRequest.getPapName()));
        papDataWrapper.setGassData(papDataService.getAllPapDataByPapGroupTypeAndMeasureAndPapName(PapGroupType.GASS,
                papDataFilterRequest.getSuccessIndicatorMeasure(), papDataFilterRequest.getPapName()));

        model.addAttribute("papFilter", new PapDataFilterRequest());
        model.addAttribute("papNameValue", papDataFilterRequest.getPapName());
        model.addAttribute("successIndicatorMeasureValue", papDataFilterRequest.getSuccessIndicatorMeasure());
        model.addAttribute("papData", papDataWrapper);
    }
}
