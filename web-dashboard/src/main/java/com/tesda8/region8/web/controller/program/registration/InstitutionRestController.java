package com.tesda8.region8.web.controller.program.registration;

import com.tesda8.region8.program.registration.model.dto.InstitutionDto;
import com.tesda8.region8.program.registration.model.dto.InstitutionFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramDto;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;
import com.tesda8.region8.program.registration.model.dto.RegisteredProgramRequestDto;
import com.tesda8.region8.program.registration.model.wrapper.ProgramRegistrationWrapper;
import com.tesda8.region8.program.registration.service.InstitutionService;
import com.tesda8.region8.program.registration.service.impl.RegisteredProgramService;
import com.tesda8.region8.util.enums.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionRestController {

    private InstitutionService institutionService;
    private RegisteredProgramService registeredProgramService;

    @Autowired
    public InstitutionRestController(InstitutionService institutionService,
                                     RegisteredProgramService registeredProgramService) {
        this.institutionService = institutionService;
        this.registeredProgramService = registeredProgramService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<InstitutionDto> getAllInstitutions() {
        return institutionService.getAllInstitution();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{sector}/sector")
    public List<InstitutionDto> getAllInstitutionByCourseSector(@PathVariable("sector")Sector sector) {
        return institutionService.getAllInstitutionByCourseSector(sector);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    public ProgramRegistrationWrapper getCourseCountPerInstitution() {
        return registeredProgramService.getCourseCountPerInstitution();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter")
    public List<InstitutionDto> getAllInstitutions(@RequestBody InstitutionFilter institutionFilter) {
        return institutionService.getAllInstitutionWithFilter(institutionFilter);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registeredPrograms/filter")
    public List<RegisteredProgramDto> getAllregisteredProgramWithFilter(@RequestBody RegisteredProgramFilter registeredProgramFilter) {
        return registeredProgramService.getAllRegisteredProgramsWithFilter(registeredProgramFilter);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registeredProgram/create")
    public void createRegisteredProgram(@RequestBody RegisteredProgramRequestDto registeredProgramDto) {
        registeredProgramService.createRegisteredProgram(registeredProgramDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public void createInstitution(@RequestBody InstitutionDto institutionDto) {
        institutionService.createInstitution(institutionDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registeredProgram/update")
    public void updateRegisteredProgram(@RequestBody RegisteredProgramRequestDto registeredProgramRequestDto) {
        registeredProgramService.updateRegisteredProgram(registeredProgramRequestDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public void updateInstitution(@RequestBody InstitutionDto institutionDto) {
        institutionService.updateInstitution(institutionDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/registeredProgram/{id}/fetch")
    public RegisteredProgramRequestDto getRegisteredProgram(@PathVariable("id") Long id) {
        return registeredProgramService.getRegisteredProgramDto(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/fetch")
    public InstitutionDto getInstitution(@PathVariable("id") Long id) {
        return institutionService.getInstitutionDto(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/registeredProgram/{id}/delete")
    public void deleteRegisteredProgram(@PathVariable("id") Long id) {
        registeredProgramService.deleteRegisteredProgram(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/delete")
    public void deleteInstitution(@PathVariable("id") Long id) {
        institutionService.deleteInstitution(id);
    }
}
