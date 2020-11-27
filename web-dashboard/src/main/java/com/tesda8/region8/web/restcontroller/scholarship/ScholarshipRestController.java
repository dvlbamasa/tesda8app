package com.tesda8.region8.web.restcontroller.scholarship;

import com.tesda8.region8.scholarship.model.dto.ScholarshipAccomplishmentDto;
import com.tesda8.region8.scholarship.model.dto.ScholarshipWrapper;
import com.tesda8.region8.scholarship.service.ScholarshipAccomplishmentService;
import com.tesda8.region8.util.enums.Month;
import com.tesda8.region8.util.enums.ScholarshipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scholarship")
public class ScholarshipRestController {

    private ScholarshipAccomplishmentService scholarshipAccomplishmentService;

    @Autowired
    public ScholarshipRestController(ScholarshipAccomplishmentService scholarshipAccomplishmentService) {
        this.scholarshipAccomplishmentService = scholarshipAccomplishmentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishment() {
        return scholarshipAccomplishmentService.getAllScholarshipAccomplishment();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{year}/year")
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYear(@PathVariable("year") Long year) {
        return scholarshipAccomplishmentService.getAllScholarshipAccomplishmentByYear(year);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{year}/year/{month}/month")
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYearAndMonth(@PathVariable("year") Long year,
                                                                                            @PathVariable("month") Month month) {
        return scholarshipAccomplishmentService.getAllScholarshipAccomplishmentByMonthAndYear(year, month);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{year}/year/{month}/month/{type}/type")
    public List<ScholarshipAccomplishmentDto> getAllScholarshipAccomplishmentByYearAndMonthAndType(@PathVariable("year") Long year,
                                                                                                   @PathVariable("month") Month month,
                                                                                                   @PathVariable("type")ScholarshipType scholarshipType) {
        return scholarshipAccomplishmentService.getAllScholarshipAccomplishmentByMonthAndYearAndType(year, month, scholarshipType);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{year}/year/{month}/month/wrapper")
    public ScholarshipWrapper getAllScholarshipAccomplishment(@PathVariable("year") Long year,
                                                                            @PathVariable("month") Month month) {
        return scholarshipAccomplishmentService.getAllScholarshipAccomplishment(year, month);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public void createScholarshipAccomplishment(@RequestBody List<ScholarshipAccomplishmentDto> scholarshipAccomplishmentDtoList,
                                                @RequestParam("type") ScholarshipType scholarshipType) {
        scholarshipAccomplishmentService.createScholarshipAccomplishment(scholarshipType, scholarshipAccomplishmentDtoList);
    }
}
