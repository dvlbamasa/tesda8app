package com.tesda8.region8.program.registration.model.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpiredDocumentsWrapper {

    private List<ExpiredRegisteredProgramDocument> expiredRegisteredProgramDocuments = Lists.newArrayList();

    private Page<ExpiredRegisteredProgramDocument> expiredRegisteredProgramDocumentPage;

    public int getTotalCount() {
        return expiredRegisteredProgramDocuments.size();
    }
}
