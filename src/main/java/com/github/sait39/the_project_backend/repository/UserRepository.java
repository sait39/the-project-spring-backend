package com.github.sait39.the_project_backend.repository;

import com.github.sait39.the_project_backend.model.Note;
import com.github.sait39.the_project_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    Optional<User> findByOauthId(String oauthId);
}
