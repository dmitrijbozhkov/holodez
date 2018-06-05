package org.nure.repositories;

import java.util.Optional;


import org.nure.models.auth.Account;
import org.nure.models.auth.Role;
import org.nure.models.auth.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Optional<Role> findByName(RoleName roleName);
}
