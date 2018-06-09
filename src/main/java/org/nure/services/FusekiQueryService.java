package org.nure.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

@Service
public class FusekiQueryService {
	
	private final static String declarationsPrefix = "PREFIX dec: <http://holodez.org/diagnostics.owl/declarations#> ";
	private final static String individualsPrefix = "PREFIX ind: <http://holodez/diagnostics.owl/individuals#> ";
	private final static String rdfPrefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
	private final static String owlPrefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#> ";
	private final static String xsdPrefix = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
	
	// SPARQL queries
	private static final String checkTypeQuery = "ASK { <ind:%1$s> rdf:type <dec:%2$s> }";
	private static final String checkExistenceQuery = "ASK { <ind:%s> ?p ?o }";
	
	private static final String queryLimit = "LIMIT %s \r\n";
	private static final String queryOffset = "OFFSET %s \r\n";
		
	public String prefixQuery(String query) {
		return rdfPrefix + owlPrefix + xsdPrefix + declarationsPrefix + individualsPrefix + query;
	}
	
	public String makeCheckTypeQuery(String id, String type) {
		String checkQuery = String.format(checkTypeQuery, id, type);
		return prefixQuery(checkQuery);
	}
	
	public String makeCheckExistanceQuery(String name) {
		String checkQuery = String.format(checkExistenceQuery, name);
		return prefixQuery(checkQuery);
	}
	
	public String setLimit(String query, int limit) {
		return query + String.format(queryLimit, limit);
	}
	
	public String setOffset(String query, int offset) {
		return query + String.format(queryOffset, offset);
	}
}
