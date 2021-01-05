package com.tesda8.region8.web.controller.audit;

import com.tesda8.region8.audit.model.dto.AuditLogFilter;
import com.tesda8.region8.audit.model.entities.AuditLog;
import com.tesda8.region8.audit.model.enums.AuditAction;
import com.tesda8.region8.audit.service.AuditLogService;
import com.tesda8.region8.audit.service.impl.AuditLogServiceImpl;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.web.controller.DefaultController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuditController extends DefaultController {

    private static Logger logger = LoggerFactory.getLogger(AuditController.class);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final AuditLogService auditLogService;

    @Autowired
    public AuditController(RegisteredProgramStatusService registeredProgramStatusService,
                           AuditLogService auditLogService) {
        super(registeredProgramStatusService);
        this.auditLogService = auditLogService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/audit")
    public String getAuditLogs(Model model) {
        Page<AuditLog> auditLogPage = auditLogService.findAll(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, new AuditLogFilter());
        model.addAttribute("auditLogs", auditLogPage.getContent());
        model.addAttribute("totalPages", auditLogPage.getTotalPages());
        model.addAttribute("totalElements", auditLogPage.getTotalElements());
        model.addAttribute("currentPage", DEFAULT_PAGE_NUMBER);
        model.addAttribute("auditLogFilter", new AuditLogFilter());
        addStatusCounterToModel(model);
        return "audit/audit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/audit/filter",
            consumes = "application/x-www-form-urlencoded")
    public String getAuditLogsFilter(Model model, AuditLogFilter auditLogFilter, BindingResult bindingResult) {
        Page<AuditLog> auditLogPage = auditLogService.findAll(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, auditLogFilter);
        model.addAttribute("auditLogs", auditLogPage.getContent());
        model.addAttribute("totalPages", auditLogPage.getTotalPages());
        model.addAttribute("totalElements", auditLogPage.getTotalElements());
        model.addAttribute("currentPage", DEFAULT_PAGE_NUMBER);
        model.addAttribute("auditLogFilter", auditLogFilter);
        addStatusCounterToModel(model);
        return "audit/audit";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/audit/pagination")
    public String getAuditLogsPage(Model model,
                                   @RequestParam("pageNumber") int pageNumber,
                                   @RequestParam(value = "auditAction", required = false) AuditAction auditAction,
                                   @RequestParam(value = "updatedBy", required = false) String updatedBy,
                                   @RequestParam(value = "auditDateFrom", required = false)
                                               String auditDateFrom,
                                   @RequestParam(value = "auditDateTo", required = false)
                                               String auditDateTo) throws ParseException {
        Date from = auditDateFrom.equals("") ? null : SIMPLE_DATE_FORMAT.parse(auditDateFrom);
        Date to = auditDateTo.equals("") ? null : SIMPLE_DATE_FORMAT.parse(auditDateTo);
        AuditLogFilter auditLogFilter = new AuditLogFilter(updatedBy, auditAction, from, to);
        Page<AuditLog> auditLogPage = auditLogService.findAll(pageNumber, DEFAULT_PAGE_SIZE, auditLogFilter);
        model.addAttribute("auditLogs", auditLogPage.getContent());
        model.addAttribute("totalPages", auditLogPage.getTotalPages());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalElements", auditLogPage.getTotalElements());
        model.addAttribute("auditLogFilter", auditLogFilter);
        addStatusCounterToModel(model);
        return "audit/audit";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/audit/{id}/details")
    public String getAuditLogDetails(ModelMap model, @PathVariable("id") Long auditLogId) {
        AuditLog auditLog = auditLogService.findAuditLogById(auditLogId);
        model.addAttribute("auditLog", auditLog);
        return "audit/audit_modal";
    }

}
