package org.nure.models.ontology.places;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.diseases.Disease;
import org.nure.models.ontology.diseases.Syndrome;

public class Country extends Place {
	
	public Country(String id, String name) {
		super(id, name);
		this.regions = new ArrayList<Region>();
	}
	
	public ArrayList<Region> regions; // has_region

	public Region getRegion(int index) {
		return regions.get(index);
	}
	public void setRegion(Region region) {
		this.regions.add(region);
	}
}
