package org.nure.repositories;


import org.nure.models.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<Account> findById(String id);
    Integer countByUsername(String username);
    Account save(Account account);
    void deleteAccountById(String id);
}
