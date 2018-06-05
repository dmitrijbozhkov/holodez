package org.nure.controllers;

import org.nure.services.ontology.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/api/")
	@Secured("ROLE_DOCTOR")
	public String getHello() {
		return "Hello!";
	}
}
