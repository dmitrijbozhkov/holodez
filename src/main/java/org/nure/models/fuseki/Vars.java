package org.nure.models.fuseki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
	@JsonSubTypes.Type(value=String.class, name="biography")
})
public class Vars {

	private List<String> vars;

	public List<String> getVars() {
		return vars;
	}

	public void setVars(List<String> vars) {
		this.vars = vars;
	}
}
