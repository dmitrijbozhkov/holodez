package org.nure.models.auth.ui;
import javax.validation.constraints.*;

import org.nure.models.auth.RoleName;

public class CreateWorkerRequest {

    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    
    @NotNull
    private RoleName role;
    
    private CreatePatientPassport passport;

    public CreatePatientPassport getPassport() {
		return passport;
	}

	public void setPassport(CreatePatientPassport passport) {
		this.passport = passport;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public RoleName getRole() {
		return role;
	}

	public void setRole(RoleName role) {
		this.role = role;
	}
}