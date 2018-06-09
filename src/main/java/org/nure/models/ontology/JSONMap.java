package org.nure.models.ontology;

import java.util.HashMap;

import org.nure.exceptions.BadRequestException;

public class JSONMap extends HashMap<String, String> {

	public String get(String key) {
		String value = super.get(key);
		if (value == null) {
			throw new BadRequestException("Field not found: " + key);
		}
		return value;
	}
}
