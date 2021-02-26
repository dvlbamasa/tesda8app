package com.tesda8.region8.web.restcontroller.planning;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.model.dto.SuccessIndicatorDataDto;
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
@RequestMapping("/api/papData")
public class PapDataRestController {

    private PapDataService papDataService;

    @Autowired
    public PapDataRestController(PapDataService papDataService) {
        this.papDataService = papDataService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<PapDataDto> fetchAllPapData() {
        return papDataService.getAllPapData();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{papGroupType}/papGroup")
    public List<PapDataDto> fetchAllPapDataByPapGroupType(@PathVariable("papGroupType")PapGroupType papGroupType) {
        return papDataService.getAllPapDataByPapGroupType(papGroupType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{papGroupType}/papGroup/filter")
    public List<SuccessIndicatorDataDto> fetchAllSuccessIndicatorsByPapGroupTypeAndMeasure(@PathVariable("papGroupType")PapGroupType papGroupType,
                                                                    @RequestParam("measure") String measure,
                                                                    @RequestParam("papName") String papName,
                                                                    @RequestParam("year") Long year) {
        return papDataService.getAllSuccessIndicatorsByFilter(papGroupType, measure, papName, year);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void updatePapData(@RequestBody  List<SuccessIndicatorDataDto> papDataDtoList) {
        papDataService.updatePapData(papDataDtoList, "TTI" );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/successIndicator/create")
    public void createSuccessIndicator(@RequestBody SuccessIndicatorDataDto successIndicatorDataDto) {
        papDataService.createSuccessIndicator(successIndicatorDataDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/successIndicator/update")
    public void updateSuccessIndicator(@RequestBody List<SuccessIndicatorDataDto> successIndicatorDataDtoList) {
        papDataService.updateSuccessIndicators(successIndicatorDataDtoList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public void createPapData(@RequestBody PapDataDto papDataDto, @RequestParam("year") Long year) {
        papDataService.createPapData(papDataDto, year);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public void deletePapData(@RequestBody PapDataDto papDataDto) {
        papDataService.deletePapData(papDataDto);
    }

}
