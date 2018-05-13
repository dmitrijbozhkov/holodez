package org.nure.models.ontology.places;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.diseases.Disease;
import org.nure.models.ontology.diseases.Syndrome;

public class Place {
	
	public Place(String id, String name) {
		this.syndromes = new ArrayList<Pair<Syndrome, Float>>();
		this.diseases = new ArrayList<Pair<Disease, Float>>();
		this.name = name;
		this.id = id;
	}
	
	public String id;
	public String name; // has_name
	public ArrayList<Pair<Syndrome, Float>> syndromes; // has_syndrome_probability
	public ArrayList<Pair<Disease, Float>> diseases; // has_disease_robability
	
	public Pair<Syndrome, Float> getSyndrome(int index) {
		return syndromes.get(index);
	}
	public void addSyndrome(Syndrome syndrome, float probability) {
		this.syndromes.add(new Pair<Syndrome, Float>(syndrome, probability));
	}
	public Pair<Disease, Float> getDisease(int index) {
		return diseases.get(index);
	}
	public void addDisease(Disease disease, float probability) {
		this.diseases.add(new Pair<Disease, Float>(disease, probability));
	}
	
}
