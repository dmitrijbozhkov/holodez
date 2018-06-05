package org.nure.models.query;

import java.util.List;

public class PossibleDiagnosisConfirmed {

	private List<String> diseasesProbability;
	private List<String> diseasesExcluded;
	private String confirmingId;
	private boolean confirmed;
	
	public List<String> getDiseasesProbability() {
		return diseasesProbability;
	}
	public void setDiseasesProbability(List<String> diseasesProbability) {
		this.diseasesProbability = diseasesProbability;
	}
	public List<String> getDiseasesExcluded() {
		return diseasesExcluded;
	}
	public void setDiseasesExcluded(List<String> diseasesExcluded) {
		this.diseasesExcluded = diseasesExcluded;
	}
	public String getConfirmingId() {
		return confirmingId;
	}
	public void setConfirmingId(String confirmingId) {
		this.confirmingId = confirmingId;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
}
