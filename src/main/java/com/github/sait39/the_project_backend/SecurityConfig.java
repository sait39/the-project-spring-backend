package com.github.sait39.the_project_backend;

import com.github.sait39.the_project_backend.handler.CustomAuthenticationHandler;
import com.github.sait39.the_project_backend.model.User;
import com.github.sait39.the_project_backend.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    public SecurityConfig(UserRepository userRepository, CustomAuthenticationHandler successHandler) {
        this.userRepository = userRepository;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/", "/login**", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                    .loginPage("/oauth2/authorization/google")
                    .defaultSuccessUrl("http://localhost:3000", true)
                )
                .logout(logout -> logout
                    .logoutSuccessUrl("http://localhost:3000")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return (userRequest) -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            // Fetch email, name, and profile picture from OAuth2 user info
            String email = oAuth2User.getAttribute("email");
            userRepository.findByEmail(email).orElseGet(() -> {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setName(oAuth2User.getAttribute("name"));
                newUser.setImageUrl(oAuth2User.getAttribute("picture"));
                return userRepository.save(newUser);
            });

            return oAuth2User;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
