package org.nure.models.ontology.patient.operation;

import java.util.ArrayList;
import java.util.Date;

public class Operation {

	public Operation(String id, String name, Date dateEnd, Date dateBegin, String status) {
		this.id = id;
		this.name = name;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
		this.status = status;
		this.workers = new ArrayList<OperationPersonel>();
		this.diary = new ArrayList<OperationDiaryEntry>();
	}
	
	public String id; 
	public String name; // has_name
	public Date dateEnd; // has_end
	public Date dateBegin; // has_begin
	public String status; // has_status
	public ArrayList<OperationDiaryEntry> diary; // has_diary_entry
	public ArrayList<OperationPersonel> workers; // has_personel
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
