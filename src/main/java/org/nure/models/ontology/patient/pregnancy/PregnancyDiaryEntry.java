package org.nure.models.ontology.patient.pregnancy;

import java.util.Date;

public class PregnancyDiaryEntry {

	public PregnancyDiaryEntry(String id, String description, Date dateEnd, Date dateBegin) {
		this.id = id;
		this.description = description;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
	}
	
	public String id;
	public String description; // has_description
	public Date dateEnd; // has_end
	public Date dateBegin; // has_begin
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
