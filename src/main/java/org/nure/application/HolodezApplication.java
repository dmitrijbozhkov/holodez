package org.nure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class HolodezApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolodezApplication.class, args);
	}
}
