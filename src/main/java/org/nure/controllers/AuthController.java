package org.nure.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.nure.exceptions.AppException;
import org.nure.exceptions.BadRequestException;
import org.nure.exceptions.ExistsException;
import org.nure.models.auth.Account;
import org.nure.models.auth.Role;
import org.nure.models.auth.RoleName;
import org.nure.models.auth.ui.ApiResponse;
import org.nure.models.auth.ui.JwtAuthenticationResponse;
import org.nure.models.auth.ui.LoginRequest;
import org.nure.models.auth.ui.PatientCreatedResponse;
import org.nure.models.ontology.ISearchOntologyAdapter;
import org.nure.models.ontology.JSONMap;
import org.nure.models.ontology.patient.Passport;
import org.nure.ontology.adapters.patient.PatientAdapter;
import org.nure.repositories.AccountRepository;
import org.nure.repositories.RoleRepository;
import org.nure.security.JwtTokenProvider;
import org.nure.services.FusekiRestService;
import org.nure.services.ontology.IdClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	public AuthController(Map<String, ISearchOntologyAdapter> searchAdapters) {
		patientAdapter = (PatientAdapter) searchAdapters.get("patient");
	}
	
	private PatientAdapter patientAdapter;
	private final static String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
	
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    private void validatePassword(String password) {
    	boolean match = password.matches(passwordPattern);
    	if (!match) {
    		throw new BadRequestException("Password must contain lower case and upper case latin letters, numbers, be at least 8 characters long and shouldn't have white spaces");
    	}
    }
    
    private RoleName matchRole(String name) {
    	if (RoleName.ROLE_PATIENT.match(name)) {
			throw new BadRequestException("Only medical workers can sign up");
		}
		if (RoleName.ROLE_MEDICAL_WORKER.match(name)) {
			return RoleName.ROLE_MEDICAL_WORKER;
		}
		if (RoleName.ROLE_DOCTOR.match(name)) {
			return RoleName.ROLE_DOCTOR;
		}
		throw new BadRequestException("No such role: " + name);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody String body) {
    	JSONMap values;
		try {
			values = patientAdapter.parseCreate(body);
		} catch (IOException ex) {
			throw new AppException("Couldn't read data, error: " + ex.getMessage());
		}
    	validatePassword(values.get("password"));
    	if(userRepository.existsByUsername(values.get("username"))) {
            throw new BadRequestException("Username is already taken");
        }
        Account user = new Account(values.get("id"), values.get("username"), values.get("password"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleName role = matchRole(values.get("role"));
        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
        String uri;
        try {
        	uri = patientAdapter.createRecord(values);
        } catch (ExistsException ex) {
        	uri = patientAdapter.promotePatient(values);
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(uri).build().toUri();
        return ResponseEntity.created(location).body(new PatientCreatedResponse(values.get("id")));
    }
}
