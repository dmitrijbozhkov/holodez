package org.nure.models.ontology.patient;

import java.util.Date;

import org.nure.models.ontology.places.City;
import org.nure.models.ontology.places.Country;
import org.nure.models.ontology.places.Region;

public class Residence {

	public Residence(String id, Date dateBegin, Date dateEnd, Country country, Region region, City city,
			String street) {
		this.id = id;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
		this.country = country;
		this.region = region;
		this.city = city;
		this.street = street;
	}
	
	public String id;
	public Date dateBegin; // has_begin
	public Date dateEnd; // has_end
	public Country country; // has_residence_country
	public Region region; // has_residence_region
	public City city; // has_residence_city
	public String street; // has_residence_street
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
}
