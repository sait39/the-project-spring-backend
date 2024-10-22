package com.github.sait39.the_project_backend;

import com.github.sait39.the_project_backend.handler.CustomAuthenticationHandler;
import com.github.sait39.the_project_backend.model.User;
import com.github.sait39.the_project_backend.repository.UserRepository;
import com.github.sait39.the_project_backend.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final CustomAuthenticationHandler successHandler;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(UserRepository userRepository,
                          CustomAuthenticationHandler successHandler,
                          CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.successHandler = successHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/", "/login/**" , "/login", "/oauth2/**", "/registration").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .oauth2Login(Customizer.withDefaults())
            .logout(Customizer.withDefaults());
//            .csrf(csrf -> csrf.disable());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
