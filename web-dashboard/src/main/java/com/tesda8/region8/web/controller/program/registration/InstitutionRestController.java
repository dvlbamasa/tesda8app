package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.util.enums.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionRestController {

    private InstitutionService institutionService;

    @Autowired
    public InstitutionRestController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<InstitutionDto> getAllInstitutions() {
        return institutionService.getAllInstitution();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sector}/sector")
    public List<InstitutionDto> getAllInstitutionByCourseSector(@PathVariable("sector")Sector sector) {
        return institutionService.getAllInstitutionByCourseSector(sector);
    }
}
