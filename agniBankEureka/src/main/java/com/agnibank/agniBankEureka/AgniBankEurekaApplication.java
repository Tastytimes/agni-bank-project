package com.agnibank.agniBankEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AgniBankEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgniBankEurekaApplication.class, args);
	}

}
