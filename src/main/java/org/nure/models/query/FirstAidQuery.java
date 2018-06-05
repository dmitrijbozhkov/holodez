package org.nure.models.query;

import java.util.List;

public class FirstAidQuery {

	private List<String> precedents;
	private String patientId;
	
	public List<String> getPrecedents() {
		return precedents;
	}
	public void setPrecedents(List<String> precedents) {
		this.precedents = precedents;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
}
