package com.tesda8.region8.web.controller;

import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import org.springframework.ui.Model;

public class DefaultController {

    private RegisteredProgramStatusService registeredProgramStatusService;

    public DefaultController(RegisteredProgramStatusService registeredProgramStatusService) {
        this.registeredProgramStatusService = registeredProgramStatusService;
    }

    protected void addStatusCounterToModel(Model model) {
        model.addAttribute("statusCounter", registeredProgramStatusService.getExpiredDocuments().getTotalCount());
    }

    protected void addExpiredDocumentsListToModel(Model model) {
        model.addAttribute("expiredDocuments", registeredProgramStatusService.getExpiredDocuments());
    }
}
