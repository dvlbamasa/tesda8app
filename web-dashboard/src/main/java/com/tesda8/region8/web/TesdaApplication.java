package com.tesda8.region8.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages = {
		"com.tesda8.region8.audit.repository",
		"com.tesda8.region8.planning.repository",
		"com.tesda8.region8.certification.repository",
		"com.tesda8.region8.program.registration.repository",
		"com.tesda8.region8.scholarship.repository",
		"com.tesda8.region8.quality.repository",
		"com.tesda8.region8.reports.repository"
})
@EntityScan(basePackages = {
		"com.tesda8.region8.audit.model",
		"com.tesda8.region8.planning.model",
		"com.tesda8.region8.certification.model",
		"com.tesda8.region8.program.registration.model",
		"com.tesda8.region8.scholarship.model",
		"com.tesda8.region8.quality.model",
		"com.tesda8.region8.reports.model",
		"com.tesda8.region8.web.model"
})
@ComponentScan(basePackages = {
		"com.tesda8.region8.audit",
		"com.tesda8.region8.planning",
		"com.tesda8.region8.certification",
		"com.tesda8.region8.program.registration",
		"com.tesda8.region8.scholarship",
		"com.tesda8.region8.quality",
		"com.tesda8.region8.reports",
		"com.tesda8.region8.web"
})
public class TesdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesdaApplication.class, args);
	}

}
