package com.zuitt.postApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @SpringBootApplication signals that this class is the starting point of the application and enables various Spring Boot features to kick in automatically
@SpringBootApplication
// @RestController signals that this class is a controller that handles web requests and produces HTTP responses
@RestController
public class PostAppApplication {

	public static void main(String[] args) {
		// SpringApplication - Class that can be used to bootstrap and launch a Spring application from a Java main method.
		SpringApplication.run(PostAppApplication.class, args);
	}

	// @GetMapping is used to assign GET method route to a controller
	// Default port: 8080
	@GetMapping("/hello")

//	public String hello () {
//		return "Hello world!";
//	}

	// @RequestParam annotation to assign a parameter to receive values in the URL
	public String hello(@RequestParam(value = "name", defaultValue = "World!") String name) {
		System.out.println(name);
		return "Hello " + name;
		// String query: ?name=value
	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "user", defaultValue = "user!") String user) {
		System.out.println(user);
		return "Good day " + user;
	}

	@GetMapping("/nameage")
	public String nameage(@RequestParam(value = "name", defaultValue = "juan") String name, @RequestParam(value = "age", defaultValue = "18") String age) {
		System.out.println(name);
		System.out.println(age);
		return "Hello " + name + "! Your age is " + age + ".";
	}
}
