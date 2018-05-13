package org.nure.models.search;

public class SearchString extends Predicate {

	public SearchString(String search) {
		super("SearchString");
		this.searchString = search;
	}
	
	public String searchString;
}
