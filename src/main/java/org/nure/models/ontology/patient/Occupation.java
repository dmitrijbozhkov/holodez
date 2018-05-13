package org.nure.models.ontology.patient;

import java.util.Date;

public class Occupation {
	
	public Occupation(String id, String occupationType, String occupationName, String firm, Date dateBegin,
			Date dateEnd) {
		this.occupationType = occupationType;
		this.occupationName = occupationName;
		this.firm = firm;
		this.dateBegin = dateBegin;
		this.dateEnd = dateEnd;
	}
	
	public String id;
	public String occupationType; // has_type
	public String occupationName; // has_name
	public String firm; // works_in
	public Date dateBegin; // has_begin
	public Date dateEnd; // has_end
	
	public String getOccupationType() {
		return occupationType;
	}
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}
	public String getOccupationName() {
		return occupationName;
	}
	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}
	public String getFirm() {
		return firm;
	}
	public void setFirm(String firm) {
		this.firm = firm;
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
	
}
