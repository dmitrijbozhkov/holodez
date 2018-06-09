package org.nure.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.nure.exceptions.AppException;
import org.nure.exceptions.BadRequestException;
import org.nure.exceptions.ResourceNotFoundException;
import org.nure.models.ontology.ICRUDOntologyAdapter;
import org.nure.models.ontology.ISearchOntologyAdapter;
import org.nure.models.ontology.JSONMap;
import org.nure.models.ontology.patient.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("api/")
public class CRUDController {

	@Autowired
	private Map<String, ISearchOntologyAdapter> searchAdapters;
	
	@Autowired
	private Map<String, ICRUDOntologyAdapter> crudAdapters;
	
	private ResponseEntity<?> makeJSONResponse(String body) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(body);
	}
	
	private ICRUDOntologyAdapter getCRUDAdapter(String model) {
		ICRUDOntologyAdapter adapter = this.crudAdapters.get(model);
		if (adapter == null) {
			return getSearchAdapter(model);
		} else {
			return adapter;
		}
	}
	
	private ISearchOntologyAdapter getSearchAdapter(String model) {
		ISearchOntologyAdapter adapter = this.searchAdapters.get(model);
		if (adapter == null) {
			throw new BadRequestException("No model named " + model);
		} else {
			return adapter;
		}
	}
	
	@GetMapping(value = "/search/{model}",
			 	params = { "q" })
	public ResponseEntity<?> searchRecords(@PathVariable("model") String model,
									   	   @RequestParam("q") String search,
									   	   @RequestParam("p") Optional<Integer> page) throws JsonParseException, JsonMappingException, IOException {
		ISearchOntologyAdapter adapter = getSearchAdapter(model);
		return ResponseEntity.ok(adapter.searchRecords(search, page));
	}
	
	// @Secured({"ROLE_DOCTOR", "ROLE_MEDICAL_WORKER"})
	@PostMapping("/crud/{model}")
	public ResponseEntity<?> createRecord(@PathVariable("model") String model,
										  @RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		ICRUDOntologyAdapter adapter = getCRUDAdapter(model);
		JSONMap values = adapter.parseCreate(body);
		String uri = adapter.createRecord(values);
		URI location = ServletUriComponentsBuilder
	               .fromCurrentContextPath().path(uri).build().toUri();
		return ResponseEntity.created(location).body("{ \"id\": \"" + values.get("id") + "\" }");
	}
	
	@PatchMapping("/crud/{model}/{id}")
	public ResponseEntity<?> editRecord(@PathVariable("model") String model, @PathVariable("id") String id,
										@RequestBody String body) throws JsonParseException, JsonMappingException, IOException {
		ICRUDOntologyAdapter adapter = getCRUDAdapter(model);
		JSONMap request = adapter.parseEdit(body);
		return ResponseEntity.ok(adapter.editRecord(id, request));
	}
	
	@GetMapping("/crud/{model}/{id}")
	public ResponseEntity<?> getRecord(@PathVariable("model") String model, @PathVariable("id") String id) throws JsonParseException, JsonMappingException, IOException {
		ICRUDOntologyAdapter adapter = getCRUDAdapter(model);
		return makeJSONResponse(adapter.getRecord(id));
	}
	
	@DeleteMapping("/crud/{model}/{id}")
	public ResponseEntity<?> deleteRecord(@PathVariable("model") String model, @PathVariable("id") String id) {
		ICRUDOntologyAdapter adapter = getCRUDAdapter(model);
		return ResponseEntity.ok(adapter.deleteRecord(id));
	}
}
