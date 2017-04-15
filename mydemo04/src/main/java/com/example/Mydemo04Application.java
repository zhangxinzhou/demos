package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Mydemo04Application {

	public static void main(String[] args) {
		SpringApplication.run(Mydemo04Application.class, args);
	}
}
