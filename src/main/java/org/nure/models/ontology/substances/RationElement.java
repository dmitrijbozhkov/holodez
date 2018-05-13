package org.nure.models.ontology.substances;

public class RationElement {

	public RationElement(String rationElementId, Element element, float elementRatio) {
		this.id = rationElementId;
		this.element = element;
		this.elementRatio = elementRatio;
	}
	
	public String id;
	public Element element; // has_element
	public float elementRatio; // has_ratio
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public float getElementRatio() {
		return elementRatio;
	}
	public void setElementRatio(float elementRatio) {
		this.elementRatio = elementRatio;
	}
}
