package com.monggovest.MonggoVestBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;

@SpringBootApplication
@EnableJpaAuditing
public class MonggoVestBackEndApplication {

	public static void main(String[] args) {

		SpringApplication.run(MonggoVestBackEndApplication.class, args);



	}

}

