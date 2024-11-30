package com.example.bookmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BookmanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmanageApplication.class, args);
	}

}
