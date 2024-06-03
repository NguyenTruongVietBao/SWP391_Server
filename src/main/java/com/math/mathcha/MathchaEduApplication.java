package com.math.mathcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MathchaEduApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathchaEduApplication.class, args);
	}

}
