package com.tesda8.region8.program.registration.model.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpiredDocumentsWrapper {

    private List<ExpiredRegisteredProgramDocument> expiredBuildingOwnership = Lists.newArrayList();
    private List<ExpiredRegisteredProgramDocument> expiredFireSafety = Lists.newArrayList();
    private List<ExpiredRegisteredProgramDocument> expiredMoaValidity = Lists.newArrayList();

    public int getTotalCount() {
        return expiredBuildingOwnership.size() + expiredFireSafety.size() + expiredMoaValidity.size();
    }
}
