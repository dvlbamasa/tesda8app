package com.tesda8.region8.program.registration.model.wrapper;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class ProgramRegistrationWrapper {

    private List<InstitutionWrapper> leyteInstitutionWrapperList = Lists.newArrayList();
    private List<InstitutionWrapper> southernLeyteInstitutionWrapperList= Lists.newArrayList();
    private List<InstitutionWrapper> biliranInstitutionWrapperList= Lists.newArrayList();
    private List<InstitutionWrapper> samarInstitutionWrapperList= Lists.newArrayList();
    private List<InstitutionWrapper> easternSamarInstitutionWrapperList= Lists.newArrayList();
    private List<InstitutionWrapper> northernSamarInstitutionWrapperList= Lists.newArrayList();
    private List<InstitutionWrapper> totalInstitutionWrapperList = Lists.newArrayList();

}
