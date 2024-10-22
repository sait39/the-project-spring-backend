package com.github.sait39.the_project_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    // Accounts can be created traditionally as well, so this is not primary key
    @Column(unique = true)
    private String oauthId;

    private String username;
    private String password;

    private String name;
    private String imageUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();  // Role-based security

    private String provider;
    private Boolean isEmailVerified;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;


    // Add roles, createdAt, and other fields as needed
}
