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

	public FusekiQueryService() throws NoSuchAlgorithmException {
		this.declarationsPrefix = "PREFIX dec: <http://holodez.org/diagnostics.owl/declarations#> ";
		this.individualsPrefix = "PREFIX ind: <http://holodez/diagnostics.owl/individuals#> ";
		this.rdfPrefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
		this.owlPrefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#> ";
		this.xsdPrefix = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";
		this.digester = MessageDigest.getInstance("MD5");
		this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	private final String declarationsPrefix;
	private final String individualsPrefix;
	private final String rdfPrefix;
	private final String owlPrefix;
	private final String xsdPrefix;
	private final MessageDigest digester;
	private final Format dateFormatter;
	
	public String prefixQuery(String query) {
		return rdfPrefix + owlPrefix + xsdPrefix + declarationsPrefix + individualsPrefix + query;
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
	
	public String dateToString(Date date) {
		return dateFormatter.format(date);
	}
}
