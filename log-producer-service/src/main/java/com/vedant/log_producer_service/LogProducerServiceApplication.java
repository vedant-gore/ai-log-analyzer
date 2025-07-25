package com.vedant.log_producer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogProducerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogProducerServiceApplication.class, args);
	}

}
