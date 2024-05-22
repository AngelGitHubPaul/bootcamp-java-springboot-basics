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

	// @RequestParam annotation to assign a paremeter to receive values added in the URL
	public String hello (@RequestParam(value = "name", defaultValue = "World!") String name) {
		System.out.println(name);
		return "Hello " + name;
		// String query: ?name=value
	}

	// s01 Activity Solution
	@GetMapping("/greeting")
	public String greeting(@RequestParam(value="user", defaultValue = "Developer") String user){
		System.out.println(user);
		return "Good day, " + user + "!";
		// http://localhost:8080/greeting?user=Jane
	}

	// s01 Stretch Goal Solution
	@GetMapping("/nameage")
	public String nameAge(@RequestParam(value = "user", defaultValue = "Developer") String user,
						  @RequestParam(value = "age", defaultValue = "0") int age) {
		System.out.println(user);
		System.out.println(age);
		return "Hello, " + user + "! You are " + age + " years old.";
		// http://localhost:8080/nameage?user=Jane&age=18
	}
}
