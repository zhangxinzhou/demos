package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProgramWithGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramWithGradleApplication.class, args);
	}
	
	@GetMapping("/index")
	public String Index(){
		return "index";
	}
}
