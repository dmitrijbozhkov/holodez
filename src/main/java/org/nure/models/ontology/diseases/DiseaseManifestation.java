package org.nure.models.ontology.diseases;

public class DiseaseManifestation {
	
	public DiseaseManifestation(String id, String status, String stage, String description,
			Precedent precedent) {
		this.id = id;
		this.status = status;
		this.stage = stage;
		this.description = description;
		this.precedent = precedent;
	}
	
	public String id;
	public String status; // has_status
	public String stage; // has_stage
	public String description; // has_description
	public Precedent precedent; // has_precedent
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Precedent getPrecedent() {
		return precedent;
	}
	public void setPrecedent(Precedent precedent) {
		this.precedent = precedent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
