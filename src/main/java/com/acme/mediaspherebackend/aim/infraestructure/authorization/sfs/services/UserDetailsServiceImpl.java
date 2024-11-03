package com.acme.mediaspherebackend.aim.infraestructure.authorization.sfs.services;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.infraestructure.authorization.sfs.model.UserDetailsImpl;
import com.acme.mediaspherebackend.aim.infraestructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetailsService interface.
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
        return UserDetailsImpl.build(user);
    }
}
