package com.microservice.ServiceTwo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.model.Student;

@RestController
public class WelcomeController {
	static List<Student> studentList=new ArrayList<>();
	@GetMapping("/showMessage")
	public String showMessage()
	{
		return "Hello from second service";
	}
	@GetMapping("/showStudent")
	public Student showStudent()
	{
		Student obj=new Student();
		obj.setRegno(1001);
		obj.setEmail("sai@gmail.com");
		obj.setName("sai");
		
		return obj;
	}
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student)
	{
		studentList.add(student);
		System.out.println(studentList);
		return student;
	}
	

}
