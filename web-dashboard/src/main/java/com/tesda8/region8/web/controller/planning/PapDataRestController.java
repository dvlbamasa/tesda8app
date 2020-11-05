package com.tesda8.region8.web.controller.planning;

import com.tesda8.region8.planning.model.dto.PapDataDto;
import com.tesda8.region8.planning.service.PapDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
