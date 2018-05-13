package org.nure.models.ontology.patient;

import java.util.ArrayList;

import org.nure.models.ontology.cases.Case;
import org.nure.models.ontology.diseases.Disease;
import org.nure.models.ontology.patient.menstruation.Menstruation;
import org.nure.models.ontology.patient.operation.Operation;
import org.nure.models.ontology.patient.people.Relative;
import org.nure.models.ontology.patient.pregnancy.Pregnancy;
import org.nure.models.ontology.substances.RationElement;

public class PatientAnamnesis {
	
	public PatientAnamnesis(String id) {
		this.id = id;
		this.occupations = new ArrayList<Occupation>();
		this.visits = new ArrayList<Visit>();
		this.diseases = new ArrayList<Disease>();
		this.operations = new ArrayList<Operation>();
		this.operations = new ArrayList<Operation>();
		this.allergies = new ArrayList<Allergy>();
		this.residences = new ArrayList<Residence>();
		this.ration = new ArrayList<RationElement>();
		this.cases = new ArrayList<Case>();
		this.relatives = new ArrayList<Relative>();
		this.sequelas = new ArrayList<Sequela>();
		this.pregnancies = new ArrayList<Pregnancy>();
	}
	
	public String id;
	public ArrayList<Occupation> occupations; // has_occupation
	public ArrayList<Visit> visits; // has_visit
	public Menstruation menstruation; // has_menstruation
	public ArrayList<Disease> diseases; // had_disease
	public ArrayList<Operation> operations; // has_operation
	public ArrayList<Allergy> allergies; // has_allergy
	public ArrayList<Residence> residences; // has_residence
	public ArrayList<RationElement> ration; // has_ration_element
	public ArrayList<Case> cases; // opened_case
	public ArrayList<Relative> relatives; // has_relative
	public ArrayList<Sequela> sequelas; // has_seqeuela
	public ArrayList<Pregnancy> pregnancies; // has_pregnancy
	
	public Occupation getCase(int index) {
		return occupations.get(index);
	}
	public void setCase(Occupation occupation) {
		this.occupations.add(occupation);
	}
	public Menstruation getMenstruation() {
		return menstruation;
	}
	public void setMenstruation(Menstruation menstruation) {
		this.menstruation = menstruation;
	}
}
