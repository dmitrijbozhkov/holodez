package org.nure.models.fuseki;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AskResponse {

	private Map<String,String> head;
	private boolean answer;
	
	public Map<String,String> getHead() {
		return head;
	}
	public void setHead(Map<String,String> head) {
		this.head = head;
	}
	
	@JsonProperty("boolean")
	public boolean getAnswer() {
		return answer;
	}
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
}
