package fr.oc.devops.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class JavaBasicAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JavaBasicAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
