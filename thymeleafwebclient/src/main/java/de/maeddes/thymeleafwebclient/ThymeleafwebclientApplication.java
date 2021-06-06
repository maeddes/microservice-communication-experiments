package de.maeddes.thymeleafwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ThymeleafwebclientApplication {


	public static void main(String[] args) {
		SpringApplication.run(ThymeleafwebclientApplication.class, args);
	}

}
