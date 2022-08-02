package com.wiley.contentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.wiley.contentmanagement"})
public class ContentmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentmanagementApplication.class, args);
	}


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ContentmanagementApplication.class);}

}
