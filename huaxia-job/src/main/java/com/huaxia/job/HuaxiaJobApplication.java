package com.huaxia.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HuaxiaJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuaxiaJobApplication.class, args);
	}
	
}
