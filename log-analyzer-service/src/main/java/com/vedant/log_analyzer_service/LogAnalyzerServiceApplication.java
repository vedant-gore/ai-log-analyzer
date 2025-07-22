package com.vedant.log_analyzer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LogAnalyzerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalyzerServiceApplication.class, args);
	}

}
