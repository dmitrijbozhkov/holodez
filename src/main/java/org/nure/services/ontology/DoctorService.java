package org.nure.services.ontology;

import org.nure.models.auth.ui.CreatePatientPassport;
import org.nure.models.auth.ui.CreateWorkerRequest;
import org.nure.services.FusekiQueryService;
import org.nure.services.FusekiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
	
	@Autowired
	private FusekiRestService fusekiRestService;
	
	@Autowired
	private FusekiQueryService fusekiQueryService;
	
	@Autowired
	private PatientService patientService;
	
	public boolean checkDoctor(String id) {
		return fusekiRestService.checkByType(IdClasses.DOCTOR.getAppendance() + id, IdClasses.DOCTOR.getOntologyClass());
	}
	
	public String createDoctor(String id, CreatePatientPassport passport) {
		return patientService.createPerson(id, IdClasses.DOCTOR, passport);
	}
}
