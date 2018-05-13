package org.nure.models.ontology.substances;

import java.util.ArrayList;

public class Food {

	public Food(String foodId, String name) {
		this.foodId = foodId;
		this.name = name;
		this.elements = new ArrayList<RationElement>();
	}
	
	public String foodId;
	public String name; // has_name
	public ArrayList<RationElement> elements; // has_element
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RationElement getRationElement(int index) {
		return elements.get(index);
	}
	public void setRationElement(RationElement cases) {
		this.elements.add(cases);
	}
}
