package org.nure.models.ontology.diseases;

import java.util.ArrayList;

import org.nure.models.ontology.substances.Element;

public class Action {

	public Action(String actionId, String name, String description) {
		this.id = actionId;
		this.name = name;
		this.description = description;
		this.tretmentRestrictions = new ArrayList<Treatment>();
		this.restrictedElements = new ArrayList<Element>();
	}
	
	public String id;
	public String name; // has_name
	public String description; // has_description
	public ArrayList<Treatment> tretmentRestrictions; // has_treatment_restrictions
	public ArrayList<Element> restrictedElements; // restrict_use_element
	
	public Treatment getTreatmentRestrictions(int index) {
		return tretmentRestrictions.get(index);
	}
	public void setTreatmentRestrictions(Treatment cases) {
		this.tretmentRestrictions.add(cases);
	}
	public Element getRestrictedElements(int index) {
		return restrictedElements.get(index);
	}
	public void setRestrictedElements(Element cases) {
		this.restrictedElements.add(cases);
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
	
}
