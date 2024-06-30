package com.umw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Miniproject03UserManagementWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Miniproject03UserManagementWebserviceApplication.class, args);
	}

}
