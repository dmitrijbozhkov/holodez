package org.nure.ontology.adapters.patient;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.nure.exceptions.AppException;
import org.nure.exceptions.BadRequestException;
import org.nure.exceptions.ExistsException;
import org.nure.models.auth.RoleName;
import org.nure.models.fuseki.Binding;
import org.nure.models.fuseki.SelectResponse;
import org.nure.models.ontology.ISearchOntologyAdapter;
import org.nure.models.ontology.JSONMap;
import org.nure.services.FusekiQueryService;
import org.nure.services.FusekiRestService;
import org.nure.services.ontology.IdClasses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.nure.models.auth.RoleName;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/*	SPARQL queries
 *	
 *	Search query
 *
SELECT ?patient ?name ?surname ?patronymic ?sex ?photo
WHERE {
  ?patient a ?person ;
     <dec:has_passport> ?pass .
  ?pass <dec:has_name> ?name ;
        <dec:has_surname> ?surname ;
        <dec:has_patronymic> ?patronymic ;
        <dec:has_sex> ?sex ;
        <dec:has_photo> ?photo .
  FILTER(?person IN (<dec:Patient>, <dec:MedicalWorker>, <dec:Doctor>)) .
  FILTER(REGEX(CONCAT(?name, ?surname, ?patronymic), '%s', 'i'))
}
  *
  *  Get query
  *  
SELECT ?name ?surname ?patronymic ?sex ?photo
WHERE {
  <ind:%s> <dec:has_passport> ?pass .
  ?pass <dec:has_name> ?name ;
        <dec:has_surname> ?surname ;
        <dec:has_patronymic> ?patronymic ;
        <dec:has_sex> ?sex ;
        <dec:has_photo> ?photo .
}
  *
  *  Create query female
  *  
INSERT DATA {
<ind:%1$s> a <dec:%2$s> . 
<ind:%3$s> a <dec:PatientAnamnesis> . 
<ind:%4$s> a <dec:Menstruation> . 
<ind:%5$s> a <dec:Passport> ; 
	<dec:has_name> '%6$s' ;
	<dec:has_surname> '%7$s' ; 
	<dec:has_patronymic> '%8$s' ; 
	<dec:has_sex> 'female' ;
	<dec:has_birthday> '%9$s'^^xsd:date .
<ind:%1$s> <dec:has_passport> <ind:%5$s> ;
	<dec:has_anamnesis> <ind:%3$s> ;
	<dec:has_menstruation> <ind:%4$s> .
}
  *
  *  Create query male
  *  
INSERT DATA {
<ind:%1$s> a <dec:%2$s> . 
<ind:%3$s> a <dec:PatientAnamnesis> . 
<ind:%4$s> a <dec:Passport> ; 
	<dec:has_name> '%5$s' ;
	<dec:has_surname> '%6$s' ; 
	<dec:has_patronymic> '%7$s' ; 
	<dec:has_sex> 'male' ;
	<dec:has_birthday> '%8$s'^^xsd:date . 
<ind:%1$s> <dec:has_passport> <ind:%4$s> ;
	<dec:has_anamnesis> <ind:%3$s> ;
}
  *
  *  Edit query
  *  
DELETE {
  ?passport ?p ?o
}
INSERT {
  ?passport <dec:has_name> '%2$s' ;
	<dec:has_surname> '%3$s' ; 
	<dec:has_patronymic> '%4$s' ; 
	<dec:has_sex> '%5$s' ;
	<dec:has_birthday> '%6$s'^^xsd:date .
}
WHERE {
  %1$s <dec:has_passport> ?passport .
  ?passport ?p ?o .
  FILTER NOT EXISTS { ?passport ?p <dec:Passport> } .
  FILTER NOT EXISTS { ?passport <dec:has_photo> ?o } .
  FILTER NOT EXISTS { ?passport <dec:has_biography> ?o } .
}
  *
  * Promote patient query
  *
DELETE { 
    ?p a ?o . 
} 
INSERT { 
	?p a <dec:%2$s> . 
}
WHERE {
  BIND(<ind:%1$s> AS ?p)
  ?p a ?o
}
  *
  *  Delete query
  *  
TODO
 */

public class PatientAdapter implements ISearchOntologyAdapter {

	public PatientAdapter(FusekiRestService restService) {
		super();
		this.restService = restService;
	}
	
	private final FusekiRestService restService;
	
	private final static int pageSize = 6;
	private final static String createFemale = "INSERT DATA {\r\n" + 
			"<ind:%1$s> a <dec:%2$s> . \r\n" + 
			"<ind:%3$s> a <dec:PatientAnamnesis> . \r\n" + 
			"<ind:%4$s> a <dec:Menstruation> . \r\n" + 
			"<ind:%5$s> a <dec:Passport> ; \r\n" + 
			"	<dec:has_name> '%6$s' ;\r\n" + 
			"	<dec:has_surname> '%7$s' ; \r\n" + 
			"	<dec:has_patronymic> '%8$s' ; \r\n" + 
			"	<dec:has_sex> 'female' ;\r\n" + 
			"	<dec:has_birthday> '%9$s'^^xsd:date .\r\n" + 
			"<ind:%1$s> <dec:has_passport> <ind:%5$s> ;\r\n" + 
			"	<dec:has_anamnesis> <ind:%3$s> ;\r\n" + 
			"	<dec:has_menstruation> <ind:%4$s> .\r\n" + 
			"}";
	private final static String createMale = "INSERT DATA {\r\n" + 
			"<ind:%1$s> a <dec:%2$s> . \r\n" + 
			"<ind:%3$s> a <dec:PatientAnamnesis> . \r\n" + 
			"<ind:%4$s> a <dec:Passport> ; \r\n" + 
			"	<dec:has_name> '%5$s' ;\r\n" + 
			"	<dec:has_surname> '%6$s' ; \r\n" + 
			"	<dec:has_patronymic> '%7$s' ; \r\n" + 
			"	<dec:has_sex> 'male' ;\r\n" + 
			"	<dec:has_birthday> '%8$s'^^xsd:date . \r\n" + 
			"<ind:%1$s> <dec:has_passport> <ind:%4$s> ;\r\n" + 
			"	<dec:has_anamnesis> <ind:%3$s> ;\r\n" + 
			"}";
	private final static String editPatient = "DELETE {\r\n" + 
			"  ?passport ?p ?o\r\n" + 
			"}\r\n" + 
			"INSERT {\r\n" + 
			"  ?passport <dec:has_name> '%2$s' ;\r\n" + 
			"	<dec:has_surname> '%3$s' ; \r\n" + 
			"	<dec:has_patronymic> '%4$s' ; \r\n" + 
			"	<dec:has_sex> '%5$s' ;\r\n" + 
			"	<dec:has_birthday> '%6$s'^^xsd:date .\r\n" + 
			"}\r\n" + 
			"WHERE {\r\n" + 
			"  %1$s <dec:has_passport> ?passport .\r\n" + 
			"  ?passport ?p ?o .\r\n" + 
			"  FILTER NOT EXISTS { ?passport ?p <dec:Passport> } .\r\n" + 
			"  FILTER NOT EXISTS { ?passport <dec:has_photo> ?o } .\r\n" + 
			"  FILTER NOT EXISTS { ?passport <dec:has_biography> ?o } .\r\n" + 
			"}";
	private final static String getPatient = "SELECT ?name ?surname ?patronymic ?sex ?photo\r\n" + 
			"WHERE {\r\n" + 
			"  <ind:%s> <dec:has_passport> ?pass .\r\n" + 
			"  ?pass <dec:has_name> ?name ;\r\n" + 
			"        <dec:has_surname> ?surname ;\r\n" + 
			"        <dec:has_patronymic> ?patronymic ;\r\n" + 
			"        <dec:has_sex> ?sex ;\r\n" + 
			"        <dec:has_photo> ?photo .\r\n" + 
			"}";
	private final static String promotePatientQuery = "DELETE { \r\n" + 
			"    ?p a ?o . \r\n" + 
			"} \r\n" + 
			"INSERT { \r\n" + 
			"	?p a <dec:%2$s> . \r\n" + 
			"}\r\n" + 
			"WHERE {\r\n" + 
			"  BIND(<ind:%1$s> AS ?p)\r\n" + 
			"  ?p a ?o\r\n" + 
			"}";
	private final static String searchPatient = "SELECT ?patient ?name ?surname ?patronymic ?sex ?photo\r\n" + 
			"WHERE {\r\n" + 
			"  ?patient a ?person ;\r\n" + 
			"     <dec:has_passport> ?pass .\r\n" + 
			"  ?pass <dec:has_name> ?name ;\r\n" + 
			"        <dec:has_surname> ?surname ;\r\n" + 
			"        <dec:has_patronymic> ?patronymic ;\r\n" + 
			"        <dec:has_sex> ?sex ;\r\n" + 
			"        <dec:has_photo> ?photo .\r\n" + 
			"  FILTER(?person IN (<dec:Patient>, <dec:MedicalWorker>, <dec:Doctor>)) .\r\n" + 
			"  FILTER(REGEX(CONCAT(?name, ?surname, ?patronymic), '%s', 'i'))\r\n" + 
			"}";
	
	private IdClasses matchRoleClass(String role) {
		if (RoleName.ROLE_PATIENT.match(role)) {
			return IdClasses.PATIENT;
		}
		if (RoleName.ROLE_MEDICAL_WORKER.match(role)) {
			return IdClasses.MEDICAL_WORKER;
		}
		if (RoleName.ROLE_DOCTOR.match(role)) {
			return IdClasses.DOCTOR;
		}
		throw new BadRequestException("No such role: " + role);
	}
	
	private JSONMap generateEntities(JSONMap body) {
		String id = restService.hexIdHash(body.get("name"),
										  body.get("surname"),
										  body.get("patronymic"),
										  body.get("birthday"));
		body.put("id", IdClasses.PATIENT.getAppendance() + id);
		body.put("anamnesis", IdClasses.PATIENT_ANAMNESIS.getAppendance() + id);
		body.put("passport", IdClasses.PASSPORT.getAppendance() + id);
		if (body.get("sex").equals("female")) {
			body.put("menstruation", IdClasses.MENSTRUATION.getAppendance() + id);
		}
		return body;
	}
	
	private String manageCreate(String query, String id) {
		ResponseEntity<String> response = restService.postUpdate(query);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AppException("Adding patient failed with code: " + response.getStatusCodeValue() + " and body: " + response.getBody());
		} else {
			return "/api/crud/patient/" + id;
		}
	}
	
	@Override
	public JSONMap parseCreate(String body) throws JsonParseException, JsonMappingException, IOException {
		JSONMap request = restService.parseAsMap(body);
		request = generateEntities(request);
		return request;
		
	}
	
	@Override
	public String createRecord(JSONMap request) throws ExistsException {
		if (restService.checkExistance(request.get("id"))) {
			throw new ExistsException("Patient already exists");
		}
		String role = matchRoleClass(request.get("role")).getOntologyClass();
		if (request.get("sex").equals("male")) {
			String query = String.format(createMale,
										 request.get("id"),
										 role,
										 request.get("anamnesis"),
										 request.get("passport"),
										 request.get("name"),
										 request.get("surname"),
										 request.get("patronymic"),
										 request.get("birthday"));
			return manageCreate(query, request.get("id"));
		} else if (request.get("sex").equals("female")) {
			String query = String.format(createFemale, 
										 request.get("id"),
										 role,
										 request.get("anamnesis"),
										 request.get("menstruation"),
										 request.get("passport"),
										 request.get("name"),
										 request.get("surname"),
										 request.get("patronymic"),
										 request.get("birthday"));
			return manageCreate(query, request.get("id"));
		} else {
			throw new BadRequestException("'sex' field should be 'male' of 'female'");
		}
	}
	
	@Override
	public JSONMap parseEdit(String body) throws JsonParseException, JsonMappingException, IOException {
		return restService.parseAsMap(body);
	}

	@Override
	public String editRecord(String id, JSONMap request) {
		String query = String.format(editPatient,
									 id,
									 request.get("name"),
									 request.get("surname"),
									 request.get("patronymic"),
									 request.get("sex"),
									 request.get("birthday"));
		ResponseEntity<String> response = restService.postUpdate(query);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AppException("Editing patient failed with code: " + response.getStatusCodeValue() + " and body: " + response.getBody());
		} else {
			return "{ \"result\": \"OK\" }";
		}
	}

	@Override
	public String getRecord(String id) throws JsonParseException, JsonMappingException, IOException {
		String query = String.format(getPatient, id);
		return restService.selectToValueResponse(query);
	}
	
	public String promotePatient(JSONMap values) {
		IdClasses patientType = matchRoleClass(values.get("role"));
		String promoteQuery = String.format(promotePatientQuery,
											values.get("id"),
											patientType.getClass());
		return manageCreate(promoteQuery, values.get("id"));
	}

	@Override
	public String deleteRecord(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchRecords(String searchString, Optional<Integer> page) throws JsonParseException, JsonMappingException, IOException {
		String searchQuery = String.format(searchPatient, searchString);
		int currPage;
		try {
			currPage = page.get();
		} catch (NoSuchElementException ex) {
			currPage = 0;
		}
		return restService.selectToValueResponse(searchQuery, pageSize, currPage * pageSize);
	}

}
