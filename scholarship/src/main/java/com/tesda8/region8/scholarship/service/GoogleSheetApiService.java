package com.tesda8.region8.scholarship.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleSheetApiService {

    void fetchGoogleSheetData() throws IOException, GeneralSecurityException;
}
