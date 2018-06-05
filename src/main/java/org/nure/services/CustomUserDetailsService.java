package org.nure.services;

import javax.transaction.Transactional;

import org.nure.models.auth.Account;
import org.nure.models.auth.AccountPrincipal;
import org.nure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    AccountRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with username
        Account user = userRepository.findByUsername(username)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with username : " + username)
        );

        return AccountPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String id) {
        Account user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return AccountPrincipal.create(user);
    }
}
