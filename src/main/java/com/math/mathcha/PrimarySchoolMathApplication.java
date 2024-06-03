package com.math.mathcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication

// @SpringBootApplication(exclude = {
//		 org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
// })
public class PrimarySchoolMathApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimarySchoolMathApplication.class, args);
	}

}
