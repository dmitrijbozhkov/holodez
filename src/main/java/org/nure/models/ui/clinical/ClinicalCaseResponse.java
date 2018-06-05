package org.nure.models.ui.clinical;

import java.util.ArrayList;

public class ClinicalCaseResponse {

	public ClinicalCaseResponse(String confirmationPrecedentId, String confirmationQuestion) {
		this.diseases = new ArrayList<ClinicalResponseDisease>();
		this.confirmationPrecedentId = confirmationPrecedentId;
		this.confirmationQuestion = confirmationQuestion;
	}
	
	public ArrayList<ClinicalResponseDisease> diseases;
	public String confirmationPrecedentId;
	public String confirmationQuestion;
	
	public String getConfirmationPrecedentId() {
		return confirmationPrecedentId;
	}
	public void setConfirmationPrecedentId(String confirmationPrecedentId) {
		this.confirmationPrecedentId = confirmationPrecedentId;
	}
	public String getConfirmationQuestion() {
		return confirmationQuestion;
	}
	public void setConfirmationQuestion(String confirmationQuestion) {
		this.confirmationQuestion = confirmationQuestion;
	}
}
