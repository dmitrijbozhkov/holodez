package org.nure.models.ontology.patient.menstruation;

import java.util.ArrayList;

import org.nure.models.ontology.diseases.Treatment;
import org.nure.models.ontology.patient.Occupation;

public class Menstruation {

	public Menstruation(String id) {
		this.id = id;
	}
	
	public String id;
	public ArrayList<Treatment> treatments; // treated_with
	public ArrayList<MenstruationManifestation> manifestations; // menstruation_manifested_by
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Treatment getTreatment(int index) {
		return treatments.get(index);
	}
	public void setTreatent(Treatment occupation) {
		this.treatments.add(occupation);
	}
	public MenstruationManifestation getManifestaton(int index) {
		return manifestations.get(index);
	}
	public void setManifestation(MenstruationManifestation occupation) {
		this.manifestations.add(occupation);
	}
}
