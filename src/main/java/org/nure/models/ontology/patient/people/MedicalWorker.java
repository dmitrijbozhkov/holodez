package org.nure.models.ontology.patient.people;

import org.nure.models.ontology.patient.Passport;

public class MedicalWorker extends Patient {
	
	public MedicalWorker(String id, String medicalWorkerId, Passport passport) {
		super(id, passport);
		this.medicalWorkerId = medicalWorkerId;
	}

	public String medicalWorkerId;

	public String getMedicalWorkerId() {
		return medicalWorkerId;
	}
	public void setMedicalWorkerId(String medicalWorkerId) {
		this.medicalWorkerId = medicalWorkerId;
	}
}
