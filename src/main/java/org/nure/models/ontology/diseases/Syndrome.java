package org.nure.models.ontology.diseases;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.substances.Element;

public class Syndrome {
	
	public Syndrome(String id, String description, String name) {
		this.id = id;
		this.description = description;
		this.name = name;
	}
	
	public String id;
	public String description; // has_description
	public String name; // has_name
	public ArrayList<Treatment> treatments; // has_treatments
	public ArrayList<Treatment> treatmentRestrictions; // has_treatment_restrictions
	public ArrayList<Element> restrictedElements; // restrict_use_element
	public ArrayList<Pair<Disease, Float>> diseases; // has_disease_probability
	
	public Treatment getTreatment(int index) {
		return treatments.get(index);
	}
	public void setTreatment(Treatment treatment) {
		this.treatments.add(treatment);
	}
	public Treatment getTreatmentRestriction(int index) {
		return treatmentRestrictions.get(index);
	}
	public void setTreatmentRestriction(Treatment treatment) {
		this.treatmentRestrictions.add(treatment);
	}
	public Element getRestrictedElement(int index) {
		return restrictedElements.get(index);
	}
	public void setRestrictedElement(Element element) {
		this.restrictedElements.add(element);
	}
	public Pair<Disease, Float> getDisease(int index) {
		return diseases.get(index);
	}
	public void setDiseases(Disease disease, float probability) {
		this.diseases.add(new Pair<Disease, Float>(disease, probability));
	}
}
