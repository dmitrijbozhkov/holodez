package org.nure.models.ontology.diseases;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.substances.Element;

public class Treatment extends Action {
	
	public Treatment(String actionId, String name, String description) {
		super(actionId, name, description);
		this.elements = new ArrayList<Element>();
		this.sideEffect = new ArrayList<Pair<Precedent, Float>>();
	}
	
	public ArrayList<Element> elements; // has_element
	public ArrayList<Pair<Precedent, Float>> sideEffect; // has_side_effect
	
	public Element getElement(int index) {
		return elements.get(index);
	}
	public void setElement(Element element) {
		this.elements.add(element);
	}
	public Pair<Precedent, Float> getSideEffect(int index) {
		return sideEffect.get(index);
	}
	public void setSideEffect(Precedent effect, float probability) {
		this.sideEffect.add(new Pair<Precedent, Float>(effect, probability));
	}
}
