package org.nure.main;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.nure.models.ontology.ICRUDFileOntologyAdapter;
import org.nure.models.ontology.ICRUDOntologyAdapter;
import org.nure.models.ontology.ISearchFileOntologyAdapter;
import org.nure.models.ontology.ISearchOntologyAdapter;
import org.nure.ontology.adapters.patient.PatientAdapter;
import org.nure.services.FusekiQueryService;
import org.nure.services.FusekiRestService;
import org.nure.services.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
@ComponentScan("org.nure")
@EnableJpaRepositories("org.nure.repositories")
@EntityScan("org.nure.models.auth")
public class HolodezApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolodezApplication.class, args);
	}
	
	@Bean @Qualifier("mainDataSource")
	public DataSource dataSource(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2)
				.build();
		return db;
	}
	
	@Bean
	public Map<String, ISearchOntologyAdapter> searchAdapters(FusekiRestService restService) {
		Map<String, ISearchOntologyAdapter> adapters = new HashMap<String, ISearchOntologyAdapter>();
		return adapters;
	}
	
	@Bean
	public Map<String, ICRUDOntologyAdapter> crudAdapters(FusekiRestService restService) {
		Map<String, ICRUDOntologyAdapter> adapters = new HashMap<String, ICRUDOntologyAdapter>();
		return adapters;
	}
	
	@Bean
	public Map<String, ISearchFileOntologyAdapter> searchFileAdapters(FusekiRestService restService, ImageService imageService) {
		Map<String, ISearchFileOntologyAdapter> adapters = new HashMap<String, ISearchFileOntologyAdapter>();
		adapters.put("patient", new PatientAdapter(restService, imageService));
		return adapters;
	}
	
	@Bean
	public Map<String, ICRUDFileOntologyAdapter> crudFileAdapters(FusekiRestService restService, ImageService imageService) {
		Map<String, ICRUDFileOntologyAdapter> adapters = new HashMap<String, ICRUDFileOntologyAdapter>();
		return adapters;
	}
}
