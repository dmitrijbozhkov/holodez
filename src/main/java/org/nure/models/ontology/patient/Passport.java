package org.nure.models.ontology.patient;

import java.util.Date;

public class Passport {
	
	public Passport(String id, String name, String surname, String patronymic, String sex, Date birthDay, String photo,
			String biography) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.sex = sex;
		this.birthDay = birthDay;
		this.photo = photo;
		this.biography = biography;
	}
	
	public String id;
	public String name; // has_name
	public String surname; // has_surname
	public String patronymic; // has_patronymic
	public String sex; // has_sex
	public Date birthDay; // has_birthday
	public String photo; // has_photo
	public String biography; // has_biography
	
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
	
}
