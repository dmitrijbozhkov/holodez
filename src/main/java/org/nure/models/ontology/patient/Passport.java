package org.nure.models.ontology.patient;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.nure.models.fuseki.Binding;
import org.nure.models.fuseki.SelectResponse;
import org.nure.models.fuseki.Vars;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Passport {
	
	public Passport() {
		super();
	}
	
	public Passport(String id, String name, String surname, String patronymic, String sex, Date birthDay,
			String photo, String biography) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.sex = sex;
		this.birthDay = birthDay;
		this.photo = photo;
		this.biography = biography;
	}
	
	private String id;
	private String name; // has_name
	private String surname; // has_surname
	private String patronymic; // has_patronymic
	private String sex; // has_sex
	private Date birthDay; // has_birthday
	private String photo; // has_photo
	private String biography; // has_biography
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public static Passport compose(Map<String, Binding> data) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date birthDay = df.parse(data.get("birthday").getValue());
		return new Passport(data.get("id").getValue(), data.get("name").getValue(), data.get("surname").getValue(),
							data.get("patronymic").getValue(), data.get("sex").getValue(), birthDay,
							data.get("photo").getValue(), data.get("biography").getValue());
	}
}
