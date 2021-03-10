package com.tesda8.region8.program.registration.service;

import com.tesda8.region8.program.registration.model.dto.RegisteredProgramFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CompendiumExcelService {

    void parseCompendium(HttpServletResponse httpServletResponse, RegisteredProgramFilter registeredProgramFilter) throws IOException;
}
