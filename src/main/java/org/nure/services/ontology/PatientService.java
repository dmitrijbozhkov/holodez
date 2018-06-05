package org.nure.services.ontology;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nure.exceptions.AppException;
import org.nure.models.auth.RoleName;
import org.nure.models.auth.ui.CreatePatientPassport;
import org.nure.models.auth.ui.CreateWorkerRequest;
import org.nure.models.fuseki.AskResponse;
import org.nure.models.fuseki.SelectResponse;
import org.nure.models.ontology.patient.Passport;
import org.nure.services.FusekiQueryService;
import org.nure.services.FusekiRestService;
import org.nure.services.ResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class PatientService {

	@Autowired
	private FusekiRestService fusekiRestService;
	
	@Autowired
	private FusekiQueryService fusekiQueryService;
	
	@Autowired
	private ResponseParser parser;
	
	// SPARQL queries
	private static final String updatePromotionPatientQuery = "DELETE {\r\n" + 
			"  	?s1 rdf:type <dec:Patient> .\r\n" + 
			"	?s1 ?p1 ?o1 .\r\n" + 
			"}\r\n" + 
			"INSERT {\r\n" + 
			"  	%2$s rdf:type <dec:%3$s> .\r\n" + 
			"	%2$s ?p1 ?o1 .\r\n" + 
			"}\r\n" + 
			"WHERE {\r\n" + 
			"	?s1 ?p1 ?o1 .\r\n" + 
			"  	FILTER(?s1 = %1$s && ?p1 != rdf:type) .\r\n" + 
			"}";
	private static final String updatePromotionLinksQuery = "DELETE {\r\n" + 
			"	?s1 ?p1 ?o1 .\r\n" + 
			"}\r\n" + 
			"INSERT {\r\n" + 
			"  	?s1 ?p1 %2$s .\r\n" + 
			"}\r\n" + 
			"WHERE {\r\n" + 
			"  	?s1 ?p1 ?o1 .\r\n" + 
			"  	FILTER(?o1 = %1$s) .\r\n" + 
			"}";
	private static final String createPatient = "INSERT DATA { \r\n" + 
			"  <ind:%1$s> a <dec:%2$s> .\r\n" + 
			"  <ind:%3$s> a <dec:PatientAnamnesis> . \r\n" + 
			"  <ind:%4$s> a <dec:Menstruation> .\r\n" + 
			"  <ind:%5$s> a <dec:Passport> ;\r\n" + 
			"  			  <dec:has_name> '%6$s' ;\r\n" + 
			"              <dec:has_surname> '%7$s' ;\r\n" + 
			"			  <dec:has_patronymic> '%8$s' ;\r\n" + 
			"              <dec:has_sex> '%9$s' ;\r\n" + 
			"			  <dec:has_birthday> '%10$s'^^xsd:date ;\r\n" + 
			"			  <dec:has_photo> '%11$s' ;\r\n" + 
			"			  <dec:has_biography> '%12$s'.\r\n" + 
			"  <ind:%1$s> <dec:has_passport> <ind:%5$s> ;\r\n" + 
			"  			<dec:has_anamnesis> <ind:%3$s> .\r\n" + 
			"  <ind:%3$s> <dec:has_menstruation> <ind:%3$s> .\r\n" + 
			"}";
	private static final String getPassport = "SELECT *\r\n" + 
			"WHERE {\r\n" + 
			"<ind:%1$s> <dec:has_biography> ?biography ;\r\n" + 
			"                       <dec:has_birthday> ?birthday ;\r\n" + 
			"                       <dec:has_name> ?name ;\r\n" + 
			"                       <dec:has_patronymic> ?patronymic ;\r\n" + 
			"                       <dec:has_photo> ?photo ;\r\n" + 
			"                       <dec:has_sex> ?sex ;\r\n" + 
			"                       <dec:has_surname> ?surname .\r\n" + 
			"  BIND('%1$s' as ?id)\r\n" + 
			"}";
	
	public String createPerson(String id, IdClasses patientType, CreatePatientPassport passport) {
		String patientId = patientType.getAppendance() + id;
		String anamnesisId = IdClasses.PATIENT_ANAMNESIS.getAppendance() + id;
		String menstruationId = IdClasses.MENSTRUATION.getAppendance() + id;
		String passportId = IdClasses.PASSPORT.getAppendance() + id;
		String birthDay = fusekiQueryService.dateToString(passport.getBirthDay());
		String createPatientQuery = String.format(createPatient,
												  patientId,
												  patientType.getOntologyClass(),
												  anamnesisId,
												  menstruationId,
												  passportId,
												  passport.getName(),
												  passport.getSurname(),
												  passport.getPatronymic(),
												  passport.getSex(),
												  birthDay,
												  passport.getPhoto(),
												  passport.getBiography());
		ResponseEntity<String> response = fusekiRestService.postUpdate(createPatientQuery);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new AppException("Cannot create " + patientType.getOntologyClass() + " with id: " + id +
					"body is: " + response.getBody());
		}
		return patientId;
	}
	
	public String createPatient(String id, CreatePatientPassport signUpRequest) {
		return createPerson(id, IdClasses.PATIENT, signUpRequest);
	}
	
	public String generatePatientEntitiesId(String name, String surname, String patronymic, Date date) {
		return fusekiQueryService.hexIdHash(name,
				   							surname,
				   							patronymic,
				   							fusekiQueryService.dateToString(date));
	}
	
	public boolean checkPatient(String id) {
		return fusekiRestService.checkByType(IdClasses.PATIENT.getAppendance() + id, IdClasses.PATIENT.getOntologyClass());
	}
	
	public String promoteRole(String id, RoleName role) throws AppException {
		String promotionPatientQuery;
		String promotionLinksQuery;
		String oldId = IdClasses.PATIENT.getAppendance() + id;
		String promotedId;
		if (role == RoleName.ROLE_DOCTOR) {
			promotedId = IdClasses.DOCTOR.getAppendance() + id;
			promotionPatientQuery = String.format(updatePromotionPatientQuery,
										   oldId,
										   promotedId,
										   IdClasses.DOCTOR.getOntologyClass());
		} else {
			promotedId = IdClasses.MEDICAL_WORKER.getAppendance() + id;
			promotionPatientQuery = String.format(updatePromotionPatientQuery,
										   oldId,
										   promotedId,
										   IdClasses.MEDICAL_WORKER.getOntologyClass());
		}
		promotionLinksQuery = String.format(updatePromotionLinksQuery,
											oldId,
											promotedId);
		ResponseEntity<String> responsePatient = fusekiRestService.postUpdate(promotionPatientQuery);
		ResponseEntity<String> responseLinks = fusekiRestService.postUpdate(promotionLinksQuery);
		if (responsePatient.getStatusCode() != HttpStatus.OK || responseLinks.getStatusCode() != HttpStatus.OK) {
			throw new AppException("Cannot promote patient id " + id);
		}
		return promotedId;
	}
	
	public Passport getPassport(String patientId) {
		String passportId = patientId.replace(IdClasses.PATIENT.getAppendance(), IdClasses.PASSPORT.getAppendance());
		String getPassportQuery = String.format(getPassport, passportId);
		ResponseEntity<String> responsePassport = fusekiRestService.postSelect(getPassportQuery);
		SelectResponse parsedResponse;
		try {
			parsedResponse = parser.parse(responsePassport);
		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage() + "Cause: " + e.getCause() + "CLass" + e.getClass());
			throw new AppException(e.getMessage());
		}
		try {
			return Passport.compose(parsedResponse.getResults().getBindings().iterator().next());
		} catch (ParseException e) {
			throw new AppException(e.getMessage());
		}
	}
}
