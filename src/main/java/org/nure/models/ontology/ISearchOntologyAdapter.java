package org.nure.models.ontology;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ISearchOntologyAdapter extends ICRUDOntologyAdapter {
	public String searchRecords(String searchString, Optional<Integer> page) throws JsonParseException, JsonMappingException, IOException;
}
