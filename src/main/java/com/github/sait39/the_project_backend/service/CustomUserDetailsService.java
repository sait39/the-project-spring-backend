package com.github.sait39.the_project_backend.service;

import com.github.sait39.the_project_backend.model.CustomUserDetails;
import com.github.sait39.the_project_backend.model.User;
import com.github.sait39.the_project_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Necessary to work with UserDetails <- needed by spring security, to handle user data using own sign up and login
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from the database by username
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Return a CustomUserDetails object
        return new CustomUserDetails(user.get());
    }

}
