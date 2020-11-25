package com.tesda8.region8.web.controller.scholarship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
public class ScholarshipController {

    @Autowired
    public ScholarshipController() {
    }

    @GetMapping("/scholarship")
    public String scholarship() throws IOException, GeneralSecurityException {
        return "scholarship/scholarship";
    }
}
