package com.github.sait39.the_project_backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(
        String name,
        String username,
        String imageUrl,
        String email,
        String oauthId,
        String provider,
        Set<String> roles,
        Boolean isEmailVerified,
        LocalDateTime createdAt,
        LocalDateTime lastLoginAt
) {}
