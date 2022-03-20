package com.lab6_2.lab6_2;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.lab6.lab6.Services.MeasurementService;

@SpringBootApplication
@ComponentScan({ "com.lab6_2.lab6_2.*" })
public class Lab62Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab62Application.class, args);
	}	
}
