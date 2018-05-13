package org.nure.models.ontology.patient.people;

import java.util.ArrayList;

import org.nure.models.ontology.cases.Case;
import org.nure.models.ontology.patient.Passport;

public class Doctor extends MedicalWorker {

	public Doctor(String id, String medicalWorkerId, Passport passport) {
		super(id, medicalWorkerId, passport);
		this.cases = new ArrayList<Case>();
	}
	
	public ArrayList<Case> cases; // has_case

	public Case getCase(int index) {
		return cases.get(index);
	}
	public void setCase(Case cases) {
		this.cases.add(cases);
	}
}
