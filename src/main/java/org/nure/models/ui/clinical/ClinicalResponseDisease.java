package org.nure.models.ui.clinical;

public class ClinicalResponseDisease {

	public ClinicalResponseDisease(String id, String name, String probability) {
		super();
		this.id = id;
		this.name = name;
		this.probability = probability;
	}
	
	public String id;
	public String name;
	public String probability;
	
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
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
}
