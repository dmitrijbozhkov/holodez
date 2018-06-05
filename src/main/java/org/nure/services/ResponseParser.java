package org.nure.services;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.nure.models.fuseki.SelectResponse;
import org.nure.models.fuseki.Vars;
import org.nure.models.ontology.patient.Passport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ResponseParser {
	
	public ResponseParser() {
		super();
		mapper = new ObjectMapper();
	}

	private ObjectMapper mapper;

	public SelectResponse parse(ResponseEntity<String> response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerSubtypes(String.class, Vars.class);
		String body = response.getBody();
		return mapper.readValue(body, SelectResponse.class);
	}
}
