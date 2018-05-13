package org.nure.models.ontology.substances;

public class Element {

	public Element(String elementId, String name) {
		this.elementId = elementId;
		this.name = name;
	}

	public String elementId;
	public String name; // has_name
	
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
