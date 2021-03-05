package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.ExpiredDocumentsWrapper;
import com.tesda8.region8.util.enums.ExpiredDocumentType;

public interface RegisteredProgramStatusService {

    long getExpiredDocumentsCount();

    ExpiredDocumentsWrapper getExpiredDocuments(int pageNumber, int pageSize, ExpiredDocumentType expiredDocumentType);
}
