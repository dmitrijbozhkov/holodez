package org.nure.models.ontology;

import java.io.IOException;

import org.nure.exceptions.ExistsException;
import org.nure.models.ui.ImageModel;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ICRUDFileOntologyAdapter {
	public String createRecord(ImageModel request) throws ExistsException;
	public String editRecord(String id, ImageModel body);
	public String getRecord(String id) throws JsonParseException, JsonMappingException, IOException;
	public String deleteRecord(String id);
	public ImageModel parseCreate(String body) throws JsonParseException, JsonMappingException, IOException;
	public ImageModel parseEdit(String body) throws JsonParseException, JsonMappingException, IOException;
}
