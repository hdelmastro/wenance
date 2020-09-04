package com.wenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
public class WenanceChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WenanceChallengeApplication.class, args);
		
		
	}

}
