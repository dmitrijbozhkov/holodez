package org.nure.models.ontology.diseases;

import java.util.ArrayList;

import org.javatuples.Pair;

public class TestAnalysis extends Action {

	public TestAnalysis(String actionId, String name, String description) {
		super(actionId, name, description);
		this.diseaseConfirmed = new ArrayList<Pair<Disease, Float>>();
		this.diseaseEliminated = new ArrayList<Disease>();
		this.syndromeConfirmed = new ArrayList<Pair<Syndrome, Float>>();
		this.syndromeEliminated = new ArrayList<Syndrome>();
	}
	
	public ArrayList<Disease> diseaseEliminated; // has_eliminated_disease
	public ArrayList<Syndrome> syndromeEliminated; // has_eliminated_syndrome
	public ArrayList<Pair<Disease, Float>> diseaseConfirmed; // has_confirmed_disease
	public ArrayList<Pair<Syndrome, Float>> syndromeConfirmed; // has_confirmed_syndrome
	
	public Disease getDiseaseEliminated(int index) {
		return diseaseEliminated.get(index);
	}
	public void setDiseaseEliminated(Disease disease) {
		this.diseaseEliminated.add(disease);
	}
	public Syndrome getSyndromeEliminated(int index) {
		return syndromeEliminated.get(index);
	}
	public void setSyndromeEliminated(Syndrome syndrome) {
		this.syndromeEliminated.add(syndrome);
	}
	public Pair<Disease, Float> getDiseaseConfirmed(int index) {
		return diseaseConfirmed.get(index);
	}
	public void setDiseaseConfirmed(Disease disease, float probability) {
		this.diseaseConfirmed.add(new Pair<Disease, Float>(disease, probability));
	}
	public Pair<Syndrome, Float> getSyndromeConfirmed(int index) {
		return syndromeConfirmed.get(index);
	}
	public void setSyndromeConfirmed(Syndrome syndrome, float probability) {
		this.syndromeConfirmed.add(new Pair<Syndrome, Float>(syndrome, probability));
	}
}
