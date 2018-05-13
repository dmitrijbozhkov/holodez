package org.nure.models.ontology.patient;

import java.util.Date;

public class Sequela {

	public Sequela(String id, String causeType, String name, Date date) {
		this.id = id;
		this.causeType = causeType;
		this.name = name;
		this.date = date;
	}
	
	public String id;
	public String causeType;
	public String name;
	public Date date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCauseType() {
		return causeType;
	}
	public void setCauseType(String causeType) {
		this.causeType = causeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
