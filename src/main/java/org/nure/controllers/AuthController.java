package org.nure.controllers;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.nure.exceptions.AppException;
import org.nure.models.auth.Account;
import org.nure.models.auth.Role;
import org.nure.models.auth.RoleName;
import org.nure.models.auth.ui.ApiResponse;
import org.nure.models.auth.ui.JwtAuthenticationResponse;
import org.nure.models.auth.ui.LoginRequest;
import org.nure.models.auth.ui.CreatePatientPassport;
import org.nure.models.auth.ui.CreateWorkerRequest;
import org.nure.models.ontology.patient.Passport;
import org.nure.repositories.AccountRepository;
import org.nure.repositories.RoleRepository;
import org.nure.security.JwtTokenProvider;
import org.nure.services.FusekiRestService;
import org.nure.services.ontology.DoctorService;
import org.nure.services.ontology.IdClasses;
import org.nure.services.ontology.MedicalWorkerService;
import org.nure.services.ontology.PatientService;
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
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    DoctorService doctorService;
    
    @Autowired
    MedicalWorkerService medicalWorkerService;

    private String addWorkerAccountRecord(CreatePatientPassport passport, String id) {
    	if (!medicalWorkerService.checkMedicalWorker(id)) {
    		return medicalWorkerService.createMedicalWorker(id, passport);
    	} else {
    		return IdClasses.MEDICAL_WORKER.getAppendance() + id;
    	}
    }
    
    private String addDoctorAccountRecord(CreatePatientPassport passport, String id) {
    	if (!doctorService.checkDoctor(id)) {
    		return doctorService.createDoctor(id, passport);
    	} else {
    		return IdClasses.DOCTOR.getAppendance() + id;
    	}
    }
    
    private String addWorkerOntologyRecord(CreateWorkerRequest signUpRequest) {
    	CreatePatientPassport passport = signUpRequest.getPassport();
    	String id = patientService.generatePatientEntitiesId(passport.getName(),
															 passport.getSurname(),
															 passport.getPatronymic(),
															 passport.getBirthDay());
    	if (patientService.checkPatient(id)) {
    		return patientService.promoteRole(id, signUpRequest.getRole());
    	} else {
    		RoleName userRole = signUpRequest.getRole();
    		if (userRole == RoleName.ROLE_MEDICAL_WORKER) {
    			return addWorkerAccountRecord(passport, id);
    		} else {
    			return addDoctorAccountRecord(passport, id);
    		}
    	}
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateWorkerRequest signUpRequest) {
        // check if user record exists
    	if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        String userId = addWorkerOntologyRecord(signUpRequest);

        // Creating medical workers account record
        Account user = new Account(userId, signUpRequest.getUsername(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(signUpRequest.getRole())
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        Account result = userRepository.save(user);

        String uri;
        if (signUpRequest.getRole() == RoleName.ROLE_MEDICAL_WORKER) {
        	uri = "/medical-worker/{id}";
        } else {
        	uri = "/doctor/{id}";
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(uri)
                .buildAndExpand(userId).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
