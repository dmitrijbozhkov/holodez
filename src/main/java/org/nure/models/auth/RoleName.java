package org.nure.models.auth;

public enum RoleName {
	ROLE_PATIENT("ROLE_PATIENT"),
	ROLE_MEDICAL_WORKER("ROLE_MEDICAL_WORKER"),
	ROLE_DOCTOR("ROLE_DOCTOR");
	
	private String patientRole;
	
	RoleName(String role) {
		this.patientRole = role;
	}
	
	public boolean match(String role) {
		return patientRole == role;
	}
}
