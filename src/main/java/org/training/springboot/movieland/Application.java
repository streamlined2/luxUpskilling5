package org.training.springboot.movieland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static final String DB_SCHEMA_NAME = "movieland";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
