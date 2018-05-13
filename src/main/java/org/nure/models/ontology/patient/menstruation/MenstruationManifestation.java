package org.nure.models.ontology.patient.menstruation;

import org.nure.models.ontology.diseases.Precedent;

public class MenstruationManifestation {

	public MenstruationManifestation(String id, Precedent precedent, int dateEnd, int dateBegin) {
		super();
		this.id = id;
		this.precedent = precedent;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
	}
	
	public String id;
	public Precedent precedent; // has_precedent
	public int dateEnd; // has_end
	public int dateBegin; // has_begin
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Precedent getPrecedent() {
		return precedent;
	}
	public void setPrecedent(Precedent precedent) {
		this.precedent = precedent;
	}
	public int getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(int dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(int dateBegin) {
		this.dateBegin = dateBegin;
	}
}
