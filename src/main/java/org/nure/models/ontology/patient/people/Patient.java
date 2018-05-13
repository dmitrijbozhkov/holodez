package org.nure.models.ontology.patient.people;

import org.nure.models.ontology.patient.Passport;

public class Patient {

	public Patient(String id, Passport passport) {
		this.patientId = id;
	}
	
	public String patientId;
	public Passport passport;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
}
