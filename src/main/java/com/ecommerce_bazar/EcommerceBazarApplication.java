package com.ecommerce_bazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.ecommerce_bazar.config")
public class EcommerceBazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBazarApplication.class, args);
	}

}
