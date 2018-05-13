package org.nure.models.ontology.patient;

import java.util.Date;

import org.nure.models.ontology.places.Place;

public class Visit {
	
	public Visit(String id, Place place, Date dateEnd, Date dateBegin) {
		this.id = id;
		this.place = place;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
	}
	public String id;
	public Place place; // has_place
	public Date dateEnd; // has_end
	public Date dateBegin; // has_begin
	
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	
}
