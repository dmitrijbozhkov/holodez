package org.nure.services.ontology;

import org.nure.models.auth.ui.CreatePatientPassport;
import org.nure.models.auth.ui.CreateWorkerRequest;
import org.nure.models.fuseki.AskResponse;
import org.nure.services.FusekiQueryService;
import org.nure.services.FusekiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MedicalWorkerService {

	@Autowired
	private FusekiRestService fusekiRestService;
	
	@Autowired
	private FusekiQueryService fusekiQueryService;
	
	@Autowired
	private PatientService patientService;
	
	public boolean checkMedicalWorker(String id) {
		return fusekiRestService.checkByType(IdClasses.MEDICAL_WORKER.getAppendance() + id, IdClasses.MEDICAL_WORKER.getOntologyClass());
	}
	
	public String createMedicalWorker(String id, CreatePatientPassport passport) {
		return patientService.createPerson(id, IdClasses.MEDICAL_WORKER, passport);
	}
}
