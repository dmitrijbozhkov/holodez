package org.nure.models.ontology.cases;

import java.util.ArrayList;
import java.util.Date;

import org.nure.models.ontology.diseases.Action;

public class CaseDiaryEntry {

	public CaseDiaryEntry(String id, String status, Date dateEnd, Date dateBegin) {
		this.id = id;
		this.status = status;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
		this.actions = new ArrayList<Action>();
	}
	
	public String id;
	public String status;
	public Date dateEnd;
	public Date dateBegin;
	public ArrayList<Action> actions;
	
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
	public Action getAction(int index) {
		return actions.get(index);
	}
	public void setAction(Action action) {
		this.actions.add(action);
	}
}
