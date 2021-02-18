package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.ExpiredDocumentsWrapper;

public interface RegisteredProgramStatusService {

    long getExpiredDocumentsCount();

    ExpiredDocumentsWrapper getExpiredDocuments();
}
