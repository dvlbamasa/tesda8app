package com.tesda8.region8.web.controller;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import org.springframework.ui.Model;

public class HeaderController {

    private RegisteredProgramStatusService registeredProgramStatusService;
    private ExpiredCertificateService expiredCertificateService;

    public HeaderController(RegisteredProgramStatusService registeredProgramStatusService,
                            ExpiredCertificateService expiredCertificateService) {
        this.registeredProgramStatusService = registeredProgramStatusService;
        this.expiredCertificateService = expiredCertificateService;
    }

    protected void addStatusCounterToModel(Model model) {
        model.addAttribute("expiredCertificateCount", expiredCertificateService.expiredCertificatesCount());
        model.addAttribute("statusCounter", registeredProgramStatusService.getExpiredDocumentsCount());
    }

    protected void addExpiredDocumentsListToModel(Model model) {
        model.addAttribute("expiredDocuments", registeredProgramStatusService.getExpiredDocuments());
    }

    protected void addExpiredCertificateListToModel(Model model, String trainerName) {
        model.addAttribute("expiredCertificatesWrapper", expiredCertificateService.getExpiredCertificates(trainerName));
    }
}
