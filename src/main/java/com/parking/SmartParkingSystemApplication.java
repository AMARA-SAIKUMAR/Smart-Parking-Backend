package com.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan( basePackages = {"com.parking"})
@EnableJpaRepositories("com.parking.Repository")
@EntityScan("com.parking.Models")
public class SmartParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartParkingSystemApplication.class, args);
		System.out.println("App running successfully at port : 8080");
	}

}
