package org.nure.models.ontology.diseases;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.substances.Element;

public class Precedent {
	
	public Precedent(String id, String type, String name, String description, String confirmationQuestion) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.description = description;
		this.confirmationQuestion = confirmationQuestion;
		this.elementRestrictions = new ArrayList<Element>();
		this.treatments = new ArrayList<Treatment>();
		this.treatmentRestrictions = new ArrayList<Treatment>();
		this.syndromes = new ArrayList<Pair<Syndrome, Float>>();
		this.diseases = new ArrayList<Pair<Disease, Float>>();
	}
	
	public String id;
	public String type; // has_type
	public String name; // has_name
	public String description; // has_description
	public String confirmationQuestion; // has_question
	public ArrayList<Element> elementRestrictions; // restrict_use_element
	public ArrayList<Treatment> treatments; // has_treatment
	public ArrayList<Treatment> treatmentRestrictions; // has_treatment_restriction
	public ArrayList<Pair<Syndrome, Float>> syndromes; // has_syndrome_probability
	public ArrayList<Pair<Disease, Float>> diseases; // has_disease_probability
	
	public Element getElelemntRestriction(int index) {
		return elementRestrictions.get(index);
	}
	public void setElelemntRestriction(Element element) {
		this.elementRestrictions.add(element);
	}
	public Treatment getTreatment(int index) {
		return treatments.get(index);
	}
	public void setTreatment(Treatment treatment) {
		this.treatments.add(treatment);
	}
	public Treatment getTreatmentRestrictions(int index) {
		return treatmentRestrictions.get(index);
	}
	public void setTreatmentRestrictions(Treatment treatment) {
		this.treatmentRestrictions.add(treatment);
	}
	public Pair<Syndrome, Float> getSyndrome(int index) {
		return syndromes.get(index);
	}
	public void setSyndrome(Syndrome syndrome, float probability) {
		this.syndromes.add(new Pair<Syndrome, Float>(syndrome, probability));
	}
	public Pair<Disease, Float> getDisease(int index) {
		return diseases.get(index);
	}
	public void setDisease(Disease disease, float probability) {
		this.diseases.add(new Pair<Disease, Float>(disease, probability));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getConfirmationQuestion() {
		return confirmationQuestion;
	}
	public void setConfirmationQuestion(String confirmationQuestion) {
		this.confirmationQuestion = confirmationQuestion;
	}
}
