package org.nure.models.fuseki;

import com.fasterxml.jackson.annotation.JsonSubTypes;

public class SelectResponse {
	
	private Vars head;
	private Bindings results;
	
	public Vars getHead() {
		return head;
	}
	public void setHead(Vars head) {
		this.head = head;
	}
	public Bindings getResults() {
		return results;
	}
	public void setResults(Bindings results) {
		this.results = results;
	}
}
