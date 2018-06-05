package org.nure.models.search;

public class Precedent extends Predicate {

	public Precedent(String name, boolean confirmed) {
		super("Precedent");
		this.precedentName = name;
		this.precedentConfirmed = confirmed;
	}
	
	public String precedentName;
	public boolean precedentConfirmed;
}
