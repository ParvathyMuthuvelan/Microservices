package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.model.Student;

@RestController
public class AccessController {
@Autowired
	RestTemplate restTemplate;
//http://localhost:8080/displayMessage
	@GetMapping("/displayMessage")
	public String displayMessage() {
		//String message = "Hello";
		//ResponseEntity<String> response=restTemplate.getForEntity("http://localhost:8081/showMessage",String.class);
		 String message=restTemplate.getForObject("http://localhost:8081/showMessage",
		 String.class);
		return message;
		//return response.getBody();
	}
	@GetMapping("/displayStudent")
	public Student displayStudent() {
		
		Student response=restTemplate.getForObject("http://localhost:8081/showStudent",Student.class);
		
		return response;
	}
	@PostMapping(value= "/addStudent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Student> createStudent(@RequestBody Student student) throws RestClientException, JsonProcessingException {		
	return restTemplate.postForEntity(
	    "http://localhost:8081/addStudent",
        student,
        Student.class);
}
}
