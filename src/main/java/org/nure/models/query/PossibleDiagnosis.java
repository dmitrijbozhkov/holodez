package org.nure.models.query;

import java.util.List;

public class PossibleDiagnosis {

	private List<DiseaseProbability> diseasesProbability;
	private List<String> diseasesExcluded;
	private String confirmationQuestion;
	private String confirmingId;
	
	public List<DiseaseProbability> getDiseasesProbability() {
		return diseasesProbability;
	}
	public void setDiseasesProbability(List<DiseaseProbability> diseasesProbability) {
		this.diseasesProbability = diseasesProbability;
	}
	public List<String> getDiseasesExcluded() {
		return diseasesExcluded;
	}
	public void setDiseasesExcluded(List<String> diseasesExcluded) {
		this.diseasesExcluded = diseasesExcluded;
	}
	public String getConfirmationQuestion() {
		return confirmationQuestion;
	}
	public void setConfirmationQuestion(String confirmationQuestion) {
		this.confirmationQuestion = confirmationQuestion;
	}
	public String getConfirmingId() {
		return confirmingId;
	}
	public void setConfirmingId(String confirmingId) {
		this.confirmingId = confirmingId;
	}
}
