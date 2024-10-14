package com.github.sait39.the_project_backend.service;

import com.github.sait39.the_project_backend.dto.UserDto;
import com.github.sait39.the_project_backend.model.User;
import com.github.sait39.the_project_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or update
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name());
        user.setImageUrl(userDto.imageUrl());
        user.setEmail(userDto.email());

        return userRepository.save(user);
    }

    // Read
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Delete
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Update only if title is provided
        Optional.ofNullable(userDto.name()).ifPresent(existingUser::setName);

        return userRepository.save(existingUser);
    }



}