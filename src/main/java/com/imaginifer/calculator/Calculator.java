package com.imaginifer.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Calculator {

	public static void main(String[] args) {
		SpringApplication.run(Calculator.class, args);
	}

}
