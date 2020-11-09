package com.tesda8.region8.web.controller.planning;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
import com.tesda8.region8.planning.model.entities.SuccessIndicatorData;
import com.tesda8.region8.planning.service.PapDataService;
import com.tesda8.region8.util.enums.PapGroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PapDataRestController {

    private PapDataService papDataService;

    @Autowired
    public PapDataRestController(PapDataService papDataService) {
        this.papDataService = papDataService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/papData")
    public List<PapDataDto> fetchAllPapData() {
        return papDataService.getAllPapData();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/papData/{papGroupType}/papGroup")
    public List<PapDataDto> fetchAllPapDataByPapGroupType(@PathVariable("papGroupType")PapGroupType papGroupType) {
        return papDataService.getAllPapDataByPapGroupType(papGroupType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/papData/{papGroupType}/papGroup/filter")
    public List<PapDataDto> fetchAllPapDataByPapGroupTypeAndMeasure(@PathVariable("papGroupType")PapGroupType papGroupType,
                                                                    @RequestParam("measure") String measure,
                                                                    @RequestParam("papName") String papName) {
        return papDataService.getAllPapDataByPapGroupTypeAndMeasureAndPapName(papGroupType, measure, papName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/papData/update")
    public void updatePapData(@RequestBody  List<PapDataDto> papDataDtoList) {
        papDataService.updatePapData(papDataDtoList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/papData/successIndicator/create")
    public void createSuccessIndicator(@RequestBody SuccessIndicatorDataDto successIndicatorDataDto) {
        papDataService.createSuccessIndicator(successIndicatorDataDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/papData/successIndicator/update")
    public void updateSuccessIndicator(@RequestBody List<PapDataDto> papDataDtoList) {
        papDataService.updateSuccessIndicators(papDataDtoList);
    }

}
