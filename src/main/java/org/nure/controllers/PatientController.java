package org.nure.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.hibernate.mapping.Map;
import org.nure.models.auth.ui.ApiResponse;
import org.nure.models.auth.ui.CreatePatientPassport;
import org.nure.models.auth.ui.CreateWorkerRequest;
import org.nure.models.auth.ui.PatientCreatedResponse;
import org.nure.services.ontology.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Secured({"ROLE_DOCTOR", "ROLE_MEDICAL_WORKER"})
	@PostMapping("/create")
	public ResponseEntity<?> createPatient(@Valid @RequestBody CreatePatientPassport createRequisites) {
		String patientId = patientService.generatePatientEntitiesId(createRequisites.getName(),
																	createRequisites.getSurname(),
																	createRequisites.getPatronymic(),
																	createRequisites.getBirthDay());
		if (patientService.checkPatient(patientId)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, "User already exists"));
		} else {
			patientId = patientService.createPatient(patientId, createRequisites);
			URI location = ServletUriComponentsBuilder
	                .fromCurrentContextPath().path("/{id}")
	                .buildAndExpand(patientId).toUri();
			return ResponseEntity.created(location).body(new PatientCreatedResponse(patientId));
		}
	}
	
	@Secured({"ROLE_DOCTOR", "ROLE_MEDICAL_WORKER"})
	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientPassport(@PathVariable("patientId") String id) {
		return ResponseEntity.ok(patientService.getPassport(id));
	}
	
}
