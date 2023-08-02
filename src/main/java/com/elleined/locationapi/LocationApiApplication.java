package com.elleined.locationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LocationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationApiApplication.class, args);
	}

}
