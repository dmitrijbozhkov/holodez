package org.nure.controllers;

import javax.validation.Valid;

import org.nure.models.query.FirstAidQuery;
import org.nure.services.RuleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/diagnose")
public class DiagnoseController {

	@Autowired
	private RuleQueryService ruleQueryService;
	
	// @Secured({"ROLE_DOCTOR", "ROLE_MEDICAL_WORKER"})
	@PostMapping("/first-aid")
	public ResponseEntity<?> firstAidDiagnostic(@Valid @RequestBody FirstAidQuery query) {
		return ResponseEntity.ok(ruleQueryService.processFirstAid(query));
	}
	
	@PostMapping("/case-update")
	public ResponseEntity<?> caseUpdateDiagnostic(@Valid @RequestBody FirstAidQuery query) {
		return ResponseEntity.ok(ruleQueryService.processFirstAid(query));
	}
}
