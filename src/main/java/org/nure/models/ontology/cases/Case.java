package org.nure.models.ontology.cases;

import java.util.ArrayList;
import java.util.Date;

import org.javatuples.Pair;
import org.nure.models.ontology.diseases.Disease;

public class Case {

	public Case(String caseId) {
		this.id = caseId;
		this.diary = new ArrayList<CaseDiaryEntry>();
		this.diseases = new ArrayList<Pair<Disease, String>>();
		this.possibleDiseases = new ArrayList<Pair<Disease, Float>>();
	}
	
	public Case(String caseId, String status, Date end, Date start) {
		this.id = caseId;
		this.status = status;
		this.dateEnd = end;
		this.dateStart = start;
		this.diary = new ArrayList<CaseDiaryEntry>();
		this.diseases = new ArrayList<Pair<Disease, String>>();
		this.possibleDiseases = new ArrayList<Pair<Disease, Float>>();
	}
	
	public String id;
	public String status; // has_status
	public Date dateEnd; // has_end
	public Date dateStart; // has_begin
	public ArrayList<CaseDiaryEntry> diary; // has_cause_diary_entry
	public ArrayList<Pair<Disease, String>> diseases; // has_diagnosis
	public ArrayList<Pair<Disease, Float>> possibleDiseases; // has_possible_diagnosis
	
	public String getId() {
		return id;
	}
	public void setId(String caseId) {
		this.id = caseId;
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
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public CaseDiaryEntry getCaseDiaryEntry(int index) {
		return diary.get(index);
	}
	public void setCaseDiaryEntry(CaseDiaryEntry cases) {
		this.diary.add(cases);
	}
	public Pair<Disease, String> getDisease(int index) {
		return diseases.get(index);
	}
	public void setDisease(Disease disease, String description) {
		this.diseases.add(new Pair<Disease, String>(disease, description));
	}
	public Pair<Disease, Float> getPossibleDisease(int index) {
		return possibleDiseases.get(index);
	}
	public void setPossibleDisease(Disease disease, Float probability) {
		this.possibleDiseases.add(new Pair<Disease, Float>(disease, probability));
	}
}
