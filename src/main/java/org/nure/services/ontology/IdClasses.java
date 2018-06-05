package org.nure.services.ontology;

public enum IdClasses {

	PASSPORT("Passport"),
	PATIENT("Patient"),
	PATIENT_ANAMNESIS("PatientAnamnesis"),
	MEDICAL_WORKER("MedicalWorker"),
	DOCTOR("Doctor"),
	PREGNANCY("Pregnancy"),
	PREGNANCY_DIARY_ENTRY("PregnancyDiaryEntry"),
	SEQUELA("Sequela"),
	RELATIVE("Relative"),
	RATION_ELEMENT("RationElement"),
	RESIDENCE("Residence"),
	ALLERGY("Allergy"),
	ALLERGY_MANIFESTATION("AllergyManifestation"),
	OPERATION("Operation"),
	PERSONEL("Personel"),
	OPERATION_DIARY_ENTRY("OperationDiaryEntry"),
	MENSTRUATION("Menstruation"),
	MENSTRUATION_MANIFESTATION("MenstruationManifestation"),
	VISIT("Visit"),
	OCCUPATION("Occupation"),
	ELEMENT("Element"),
	FOOD("Food"),
	FOOD_ELEMENT("FoodElement"),
	CASE("Case"),
	DISEASE_PROBABILITY("DiseaseProbability"),
	CASE_DIARY_ENTRY("CaseDiaryEntry"),
	CASE_DIAGNOSTICS("CaseDiagnostics"),
	DISEASE("Disease"),
	DISEASE_MANIFESTATION("DiseaseManifestation"),
	PRECEDENT("Precedent"),
	SYNDROME_PROBABILITY("SyndromeProbability"),
	SYNDROME("Syndrome"),
	TREATMENT("Treatment"),
	SIDE_EFFECT("SideEffect"),
	TEST_ANALYSIS("TestAnalysis"),
	CONFIRMED_TEST_DISEASES("ConfirmedTestDiseases"),
	CONFIRMED_TEST_SYNDROME("ConfirmedTestSyndrome"),
	COUNTRY("Country"),
	REGION("Region"),
	CITY("City");
	
	private String ontologyClass;
	 
	IdClasses(String ontologyClass) {
        this.ontologyClass = ontologyClass;
    }
 
    public String getAppendance() {
        return ontologyClass + "-";
    }
    
    public String getOntologyClass() {
    	return ontologyClass;
    }
    
}
