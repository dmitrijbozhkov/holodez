package org.nure.main;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.nure")
public class HolodezApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolodezApplication.class, args);
	}

}
