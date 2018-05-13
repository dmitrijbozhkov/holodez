package org.nure.models.ontology.patient;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.diseases.Precedent;
import org.nure.models.ontology.diseases.Treatment;

public class Allergy {

	public Allergy(String id) {
		this.id = id;
		this.triggers = new ArrayList<Precedent>();
		this.treatments = new ArrayList<Treatment>();
		this.manifestations = new ArrayList<Pair<Precedent, Integer>>();
	}
	
	public String id;
	public ArrayList<Precedent> triggers; // triggered_by
	public ArrayList<Treatment> treatments; // has_treatment
	public ArrayList<Pair<Precedent, Integer>> manifestations; // allergy_manigested_by
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
