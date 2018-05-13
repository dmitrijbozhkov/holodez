package org.nure.models.ontology.diseases;

import java.util.ArrayList;

import org.nure.models.ontology.substances.Element;

public class Disease {
	
	public Disease(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.treatments = new ArrayList<Treatment>();
		this.treatmentRestrictions = new ArrayList<Treatment>();
		this.restrictedElements = new ArrayList<Element>();
		this.manifestations = new ArrayList<DiseaseManifestation>();
	}
	
	public String id;
	public String name; // has_name
	public String description; // has_description
	public ArrayList<Treatment> treatments; // has_treatment
	public ArrayList<Treatment> treatmentRestrictions; // has_treatment_restriction
	public ArrayList<Element> restrictedElements; // restrict_use_element
	public ArrayList<DiseaseManifestation> manifestations; // has_manifestations
	
	public Treatment getTreatment(int index) {
		return treatments.get(index);
	}
	public void setTreatment(Treatment element) {
		this.treatments.add(element);
	}
	public Treatment getTreatmentRestriction(int index) {
		return treatmentRestrictions.get(index);
	}
	public void setTreatmentRestrictions(Treatment element) {
		this.treatmentRestrictions.add(element);
	}
	public Element getRestrictedElements(int index) {
		return restrictedElements.get(index);
	}
	public void setRestrictedElements(Element element) {
		this.restrictedElements.add(element);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public DiseaseManifestation getDiseaseManifestation(int index) {
		return manifestations.get(index);
	}
	public void setDiseaseManifestation(DiseaseManifestation manifestation) {
		this.manifestations.add(manifestation);
	}
}
