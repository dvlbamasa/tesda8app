package com.tesda8.region8.web.controller.planning;

import com.tesda8.region8.planning.service.PapDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanningController {

    private PapDataService papDataService;

    @Autowired
    public PlanningController(PapDataService papDataService) {
        this.papDataService = papDataService;
    }

    @GetMapping("/planning")
    public String showPlanning(Model model) {
        return "planning/planning";
    }
}
