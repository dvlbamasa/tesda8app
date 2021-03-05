package com.tesda8.region8.web.controller;

import com.tesda8.region8.certification.model.dto.ExpiredCertificateWrapper;
import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.model.dto.ExpiredDocumentsWrapper;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.util.enums.ExpiredCertificateType;
import com.tesda8.region8.util.enums.ExpiredDocumentType;
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

    protected void addExpiredDocumentsListToModel(int pageNumber, int pageSize, ExpiredDocumentType expiredDocumentType, Model model) {
        ExpiredDocumentsWrapper expiredDocumentsWrapper = registeredProgramStatusService.getExpiredDocuments(pageNumber, pageSize, expiredDocumentType);
        model.addAttribute("expiredDocumentsWrapper", expiredDocumentsWrapper);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", expiredDocumentsWrapper.getExpiredRegisteredProgramDocumentPage().getTotalPages());
        model.addAttribute("expiredDocuments", expiredDocumentsWrapper.getExpiredRegisteredProgramDocumentPage().getContent());
    }

    protected void addExpiredCertificateListToModel(Model model, int pageNumber, int pageSize, String trainerName, ExpiredCertificateType expiredCertificateType) {
        ExpiredCertificateWrapper expiredCertificateWrapper = expiredCertificateService.getExpiredCertificates(pageNumber, pageSize, trainerName, expiredCertificateType);
        model.addAttribute("expiredCertificatesWrapper", expiredCertificateWrapper);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", expiredCertificateWrapper.getExpiredCertificateDetailsPage().getTotalPages());
        model.addAttribute("expiredCertificates", expiredCertificateWrapper.getExpiredCertificateDetailsPage().getContent());
    }
}
