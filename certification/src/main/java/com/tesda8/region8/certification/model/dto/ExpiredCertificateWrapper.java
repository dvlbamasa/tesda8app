package com.tesda8.region8.certification.model.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpiredCertificateWrapper {

    private List<ExpiredCertificateDetails> expiredNC = Lists.newArrayList();
    private List<ExpiredCertificateDetails> expiredTMC = Lists.newArrayList();
    private List<ExpiredCertificateDetails> expiredNTTC = Lists.newArrayList();

    public int getTotalCount() {
        return expiredNC.size() + expiredTMC.size() + expiredNTTC.size();
    }
}
