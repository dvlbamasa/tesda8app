package com.tesda8.region8.web.controller.scholarship;

import com.tesda8.region8.certification.service.ExpiredCertificateService;
import com.tesda8.region8.program.registration.service.RegisteredProgramStatusService;
import com.tesda8.region8.scholarship.model.dto.ScholarshipFilter;
import com.tesda8.region8.scholarship.model.dto.ScholarshipGraphFilter;
import com.tesda8.region8.scholarship.model.dto.ScholarshipWrapper;
import com.tesda8.region8.scholarship.service.ScholarshipAccomplishmentService;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.ScholarshipType;
import com.tesda8.region8.util.service.ApplicationUtil;
import com.tesda8.region8.web.controller.HeaderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
public class ScholarshipController extends HeaderController {

    private final ScholarshipAccomplishmentService scholarshipAccomplishmentService;
    private final Month CURRENT_MONTH = ApplicationUtil.getCurrentMonth();
    private final Long CURRENT_YEAR = ApplicationUtil.getCurrentYear();

    @Autowired
    public ScholarshipController(ScholarshipAccomplishmentService scholarshipAccomplishmentService,
                                 RegisteredProgramStatusService registeredProgramStatusService,
                                 ExpiredCertificateService expiredCertificateService) {
        super(registeredProgramStatusService, expiredCertificateService);
        this.scholarshipAccomplishmentService = scholarshipAccomplishmentService;
    }

    @GetMapping("/scholarship")
    public String scholarship(Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(CURRENT_YEAR, CURRENT_MONTH);
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", new ScholarshipFilter(CURRENT_YEAR, CURRENT_MONTH));
        addStatusCounterToModel(model);
        return "scholarship/scholarship";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/filter")
    public String getScholarshipsWithFilter(@ModelAttribute ScholarshipFilter scholarshipFilter, Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService
                .getAllScholarshipAccomplishment(scholarshipFilter.getYear(), scholarshipFilter.getMonth());
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", scholarshipFilter);
        addStatusCounterToModel(model);
        return "scholarship/scholarship";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scholarship/update")
    public String updateScholarship(@RequestParam("year") Long year,
                                    @RequestParam("month") Month month,
                                    Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(year, month);
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", new ScholarshipFilter(year, month));
        addStatusCounterToModel(model);
        return "scholarship/create_scholarship";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/update/filter")
    public String updateScholarship(@ModelAttribute ScholarshipFilter scholarshipFilter, Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService
                .getAllScholarshipAccomplishment(scholarshipFilter.getYear(), scholarshipFilter.getMonth());
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", scholarshipFilter);
        addStatusCounterToModel(model);
        return "scholarship/create_scholarship";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scholarship/graph")
    public String scholarshipGraph(@RequestParam("year") Long year, @RequestParam("scholarshipType") ScholarshipType scholarshipType,
                                   Model model) {
        model.addAttribute("filter", new ScholarshipGraphFilter(year, scholarshipType));
        addStatusCounterToModel(model);
        return "scholarship/scholarship_graph";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scholarship/graph/perPO")
    public String scholarshipGraph(@RequestParam(value = "month", required = false) Month month,
                                   @RequestParam("year") Long year,
                                   @RequestParam("scholarshipType") ScholarshipType scholarshipType,
                                   Model model) {
        model.addAttribute("filter", new ScholarshipGraphFilter(year,
                Optional.ofNullable(month).orElse(ApplicationUtil.getCurrentMonth()), scholarshipType));
        addStatusCounterToModel(model);
        return "scholarship/scholarship_graph_per_po";
    }

    @GetMapping("/dashboard/scholarship/live")
    public String scholarshipLive(Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(CURRENT_YEAR, CURRENT_MONTH);
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("dateTimeNow", ApplicationUtil.getLocalDateTimeNow());
        addStatusCounterToModel(model);
        return "dashboard/live_scholarship";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scholarship/live/save")
    public String scholarshipLiveWithFilter(Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(CURRENT_YEAR, CURRENT_MONTH);
        model.addAttribute("filter", new ScholarshipFilter(CURRENT_YEAR, CURRENT_MONTH));
        model.addAttribute("scholarships", scholarshipWrapper);
        addStatusCounterToModel(model);
        return "scholarship/save_live_scholarship";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/live/save/filter")
    public String scholarshipLiveWithFilter(@ModelAttribute ScholarshipFilter scholarshipFilter, Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService
                .getAllScholarshipAccomplishment(scholarshipFilter.getYear(), scholarshipFilter.getMonth());
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", scholarshipFilter);
        addStatusCounterToModel(model);
        return "scholarship/save_live_scholarship";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/update/save")
    public String updateScholarshipSave(@ModelAttribute ScholarshipWrapper scholarshipWrapper, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        scholarshipAccomplishmentService.updateScholarshipAccomplishment(scholarshipWrapper);
        return "redirect:/scholarship";
    }
}
