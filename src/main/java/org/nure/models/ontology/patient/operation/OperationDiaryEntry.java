package org.nure.models.ontology.patient.operation;

import java.util.Date;

public class OperationDiaryEntry {

	public OperationDiaryEntry(String id, String status, String action, Date dateEnd, Date dateBegin) {
		this.id = id;
		this.status = status;
		this.action = action;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
	}
	
	public String id;
	public String status; // has_status
	public String action; // action_taken
	public Date dateEnd; // has_end
	public Date dateBegin; // has_begin
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
