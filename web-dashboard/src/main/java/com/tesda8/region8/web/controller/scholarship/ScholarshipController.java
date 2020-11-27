package com.tesda8.region8.web.controller.scholarship;

import com.tesda8.region8.scholarship.model.dto.ScholarshipFilter;
import com.tesda8.region8.scholarship.model.dto.ScholarshipWrapper;
import com.tesda8.region8.scholarship.service.ScholarshipAccomplishmentService;
import com.tesda8.region8.util.enums.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ScholarshipController {

    private ScholarshipAccomplishmentService scholarshipAccomplishmentService;

    @Autowired
    public ScholarshipController(ScholarshipAccomplishmentService scholarshipAccomplishmentService) {
        this.scholarshipAccomplishmentService = scholarshipAccomplishmentService;
    }

    @GetMapping("/scholarship")
    public String scholarship(Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(2020L, Month.DECEMBER);
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", new ScholarshipFilter(2020L, Month.DECEMBER));
        return "scholarship/scholarship";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/filter")
    public String getScholarshipsWithFilter(@ModelAttribute ScholarshipFilter scholarshipFilter, Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService
                .getAllScholarshipAccomplishment(scholarshipFilter.getYear(), scholarshipFilter.getMonth());
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", scholarshipFilter);
        return "scholarship/scholarship";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/scholarship/update")
    public String updateScholarship(@RequestParam("year") Long year,
                                    @RequestParam("month") Month month,
                                    Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(year, month);
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", new ScholarshipFilter(year, month));
        return "scholarship/create_scholarship";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/update/filter")
    public String updateScholarship(@ModelAttribute ScholarshipFilter scholarshipFilter, Model model) {
        ScholarshipWrapper scholarshipWrapper = scholarshipAccomplishmentService
                .getAllScholarshipAccomplishment(scholarshipFilter.getYear(), scholarshipFilter.getMonth());
        model.addAttribute("scholarships", scholarshipWrapper);
        model.addAttribute("filter", scholarshipFilter);
        return "scholarship/create_scholarship";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scholarship/update/save")
    public String updateScholarshipSave(@ModelAttribute ScholarshipWrapper scholarshipWrapper, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //errors processing
        }
        scholarshipAccomplishmentService.updateScholarshipAccomplishment(scholarshipWrapper);
        ScholarshipWrapper scholarshipWrapper1 = scholarshipAccomplishmentService.getAllScholarshipAccomplishment(2020L, Month.DECEMBER);
        model.addAttribute("scholarships", scholarshipWrapper1);
        model.addAttribute("filter", new ScholarshipFilter(2020L, Month.DECEMBER));
        return "scholarship/scholarship";
    }
}
