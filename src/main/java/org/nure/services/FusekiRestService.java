package org.nure.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.nure.exceptions.AppException;
import org.nure.models.fuseki.AskResponse;
import org.nure.models.fuseki.Binding;
import org.nure.models.fuseki.SelectResponse;
import org.nure.models.fuseki.Vars;
import org.nure.models.ontology.JSONMap;
import org.nure.models.ui.ImageModel;
import org.nure.services.ontology.IdClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FusekiRestService {

	public FusekiRestService(@Value("${fuseki.address}") String ontologyPath) throws NoSuchAlgorithmException {
		this.queryPath = ontologyPath + "query";
		this.updatePath = ontologyPath + "update";
		this.template = new RestTemplate();
		this.digester = MessageDigest.getInstance("MD5");
	}
	
	private final RestTemplate template;
	
	private final String queryPath;
	private final String updatePath;
	private final MessageDigest digester;
	
	@Autowired
	private FusekiQueryService fusekiQueryService;
	
	private HttpEntity<MultiValueMap<String, String>> prepareRequest(String query, String type) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		MultiValueMap<String, String> content = new LinkedMultiValueMap<String, String>();
		content.set(type, query);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(content, headers);
		return request;
	}
	
	public ResponseEntity<String> postSelect(String query) {
		String path = queryPath;
		query = fusekiQueryService.prefixQuery(query);
		HttpEntity<MultiValueMap<String, String>> header = prepareRequest(query, "query");
		return template.exchange(path, HttpMethod.POST, header, String.class);
	}
	
	public ResponseEntity<String> postSelect(String query, int limit, int offset) {
		if (limit != 0) {
			query = fusekiQueryService.setLimit(query, limit);
		}
		if (offset != 0) {
			query = fusekiQueryService.setOffset(query, offset);
		}
		return postSelect(query);
	}
	
	public ResponseEntity<AskResponse> postQuestion(String query) {
		String path = queryPath;
		query = fusekiQueryService.prefixQuery(query);
		HttpEntity<MultiValueMap<String, String>> header = prepareRequest(query, "query");
		return template.exchange(path, HttpMethod.POST, header, AskResponse.class);
	}
	
	public ResponseEntity<String> postUpdate(String query) {
		String path = updatePath;
		query = fusekiQueryService.prefixQuery(query);
		HttpEntity<MultiValueMap<String, String>> header = prepareRequest(query, "update");
		return template.exchange(path, HttpMethod.POST, header, String.class);
	}
	
	public boolean checkByType(String id, String type) {
		String checkQuery = fusekiQueryService.makeCheckTypeQuery(id, type);
		ResponseEntity<AskResponse> response = postQuestion(checkQuery);
		return response.getBody().getAnswer();
	}
	
	public boolean checkExistance(String name) {
		String checkQuery = fusekiQueryService.makeCheckExistanceQuery(name);
		ResponseEntity<AskResponse> response = postQuestion(checkQuery);
		return response.getBody().getAnswer(); 
	}
	
	public SelectResponse parseSelect(String encoded) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerSubtypes(String.class, Vars.class);
		return mapper.readValue(encoded, SelectResponse.class);
	}
	
	public JSONMap parseAsMap(String encoded) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(encoded, JSONMap.class);
	}
	
	public ImageModel parseAsImageModel(String encoded) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(encoded, ImageModel.class);
	}
	
	public String hexIdHash(String... strings) {
		String result = String.join("", strings);
		byte[] encodedResult;
		try {
			encodedResult = result.getBytes("UTF-8");
		} catch (UnsupportedEncodingException ex) {
			encodedResult = result.getBytes();
		}
		byte[] hash = digester.digest(encodedResult);
		return Hex.encodeHexString(hash);
	}
	
	public List<Map<String, String>> selectResponseValues(SelectResponse response) {
		Collection<Map<String, Binding>> bindings = response.getResults().getBindings();
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		Map<String, String> row;
		for(Map<String, Binding> item : bindings) {
			row = new HashMap<String, String>();
			for(String key : item.keySet()) {
				row.put(key, item.get(key).getValue());
			}
			values.add(row);
		}
		return values;
	}
	
	public String JSONIfyMap(Map<String, List<Map<String, String>>> values) throws JsonProcessingException  {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(values);
	}
	
	public String selectToValueResponse(String query, int limit, int offset) throws JsonParseException, JsonMappingException, IOException {
		ResponseEntity<String> response = postSelect(query, limit, offset);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AppException("Action failed with code: " + response.getStatusCodeValue() + " and body: " + response.getBody());
		}
		SelectResponse parsedResponse = parseSelect(response.getBody());
		List<Map<String, String>> vals = selectResponseValues(parsedResponse);
		Map<String, List<Map<String, String>>> values = new HashMap<String, List<Map<String, String>>>();
		values.put("data", vals);
		return JSONIfyMap(values);
	}
	
	public String selectToValueResponse(String query) throws JsonParseException, JsonMappingException, IOException {
		return selectToValueResponse(query, 0, 0);
	}
}
