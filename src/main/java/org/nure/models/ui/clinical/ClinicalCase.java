package org.nure.models.ui.clinical;

import java.util.ArrayList;

public class ClinicalCase {

	public ClinicalCase() {
		this.confirmedPrecedentIds = new ArrayList<String>();
	}
	
	public ArrayList<String> confirmedPrecedentIds;
	public String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
