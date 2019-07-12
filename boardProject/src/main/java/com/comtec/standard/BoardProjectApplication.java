package com.comtec.standard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class BoardProjectApplication {
	
	@RequestMapping("/hello")
	public String helloWorld() {
		return "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectApplication.class, args);
	}

}
