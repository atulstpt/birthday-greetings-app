package com.agstechcloud.birthdayjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BirthdayEmailProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthdayEmailProjectApplication.class, args);
	}

}
