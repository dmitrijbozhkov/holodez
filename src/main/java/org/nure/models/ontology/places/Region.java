package org.nure.models.ontology.places;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.nure.models.ontology.diseases.Disease;
import org.nure.models.ontology.diseases.Syndrome;

public class Region extends Place {
	
	public Region(String id, String name) {
		super(id, name);
		this.cities = new ArrayList<City>();
	}
	
	public ArrayList<City> cities; // has_city

	public City getCity(int index) {
		return cities.get(index);
	}
	public void setCity(City region) {
		this.cities.add(region);
	}
}
