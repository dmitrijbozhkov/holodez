package org.nure.models.ontology.patient.pregnancy;

import java.util.ArrayList;
import java.util.Date;

public class Pregnancy {

	public Pregnancy(String id, String status, Date dateEnd, Date dateBegin) {
		this.id = id;
		this.status = status;
		this.dateEnd = dateEnd;
		this.dateBegin = dateBegin;
		this.pregnancyDiary = new ArrayList<PregnancyDiaryEntry>();
	}
	
	public String id;
	public String status; // has_staus
	public Date dateEnd; // has_end
	public Date dateBegin; // has_begin
	public ArrayList<PregnancyDiaryEntry> pregnancyDiary; // has_pregnancy_diary_entry
}
