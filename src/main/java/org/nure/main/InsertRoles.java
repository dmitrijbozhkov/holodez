package org.nure.main;

import org.nure.models.auth.Role;
import org.nure.models.auth.RoleName;
import org.nure.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;

import io.micrometer.core.lang.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InsertRoles implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	@NonNull
    private RoleRepository roleRepository;
 
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (roleRepository.count() > 0) {
            return;
        }
 
        roleRepository.save(new Role(RoleName.ROLE_DOCTOR));
        roleRepository.save(new Role(RoleName.ROLE_MEDICAL_WORKER));
    }
}
