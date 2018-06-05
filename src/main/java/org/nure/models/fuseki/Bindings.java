package org.nure.models.fuseki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSubTypes;

public class Bindings {

	private Collection<Map<String, Binding>> bindings;

	public Collection<Map<String, Binding>> getBindings() {
		return bindings;
	}

	public void setBindings(Collection<Map<String, Binding>> bindings) {
		this.bindings = bindings;
	}
}
