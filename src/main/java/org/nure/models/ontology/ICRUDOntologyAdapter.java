package org.nure.models.ontology;

import java.io.IOException;

import org.nure.exceptions.ExistsException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ICRUDOntologyAdapter {
	public String createRecord(JSONMap request) throws ExistsException;
	public String editRecord(String id, JSONMap body);
	public String getRecord(String id) throws JsonParseException, JsonMappingException, IOException;
	public String deleteRecord(String id);
	public JSONMap parseCreate(String body) throws JsonParseException, JsonMappingException, IOException;
	public JSONMap parseEdit(String body) throws JsonParseException, JsonMappingException, IOException;
}
