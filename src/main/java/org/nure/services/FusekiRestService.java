package org.nure.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.nure.models.fuseki.AskResponse;
import org.nure.models.fuseki.SelectResponse;
import org.nure.services.ontology.IdClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class FusekiRestService {

	public FusekiRestService(@Value("${fuseki.address}") String ontologyPath) {
		this.queryPath = ontologyPath + "query";
		this.updatePath = ontologyPath + "update";
		this.template = new RestTemplate();
	}
	
	private final RestTemplate template;
	
	private final String queryPath;
	private final String updatePath;
	
	@Autowired
	private FusekiQueryService fusekiQueryService;
	
	// SPARQL queries
	private static final String checkTypeQuery = "ASK { <ind:%1$s> rdf:type <dec:%2$s> }";
	private static final String checkExistenceQuery = "ASK { <ind:%s> ?p ?o }";
	
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
		String checkQuery = String.format(checkTypeQuery, id, type);
		checkQuery = fusekiQueryService.prefixQuery(checkQuery);
		ResponseEntity<AskResponse> response = postQuestion(checkQuery);
		return response.getBody().getAnswer();
	}
	
	public boolean checkExistance(String name) {
		String checkQuery = String.format(checkExistenceQuery, name);
		checkQuery = fusekiQueryService.prefixQuery(checkQuery);
		ResponseEntity<AskResponse> response = postQuestion(checkQuery);
		return response.getBody().getAnswer(); 
	}
}
