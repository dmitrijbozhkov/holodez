package org.nure.models.ontology;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ISearchFileOntologyAdapter extends ICRUDFileOntologyAdapter {
	public String searchRecords(String searchString, Optional<Integer> page) throws JsonParseException, JsonMappingException, IOException;
}
