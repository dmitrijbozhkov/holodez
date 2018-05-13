package org.nure.models.ontology.patient.people;

public class Relative {

	public Relative(String id, Patient person, String status) {
		this.id = id;
		this.person = person;
		this.status = status;
	}
	
	public String id;
	public Patient person;
	public String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Patient getPerson() {
		return person;
	}
	public void setPerson(Patient person) {
		this.person = person;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
