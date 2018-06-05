package org.nure.models.auth.ui;

public class PatientCreatedResponse {

	public PatientCreatedResponse(String id) {
		this.id = id;
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
