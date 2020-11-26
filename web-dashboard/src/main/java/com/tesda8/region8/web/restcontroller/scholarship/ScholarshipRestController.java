package com.tesda8.region8.web.restcontroller.scholarship;

import com.tesda8.region8.scholarship.service.ScholarshipAccomplishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scholarship")
public class ScholarshipRestController {

    private ScholarshipAccomplishmentService scholarshipAccomplishmentService;

    @Autowired
    public ScholarshipRestController(ScholarshipAccomplishmentService scholarshipAccomplishmentService) {
        this.scholarshipAccomplishmentService = scholarshipAccomplishmentService;
    }
}
