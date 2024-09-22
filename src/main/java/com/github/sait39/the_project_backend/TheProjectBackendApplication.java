package com.github.sait39.the_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class TheProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheProjectBackendApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("HelloIIII %s!", name);
	}

	@GetMapping("/notes")
	public String[] notes() {
		return new String[]{"Hi", "Why", "Shy", "Bry", "Bongo"};
	}
}
