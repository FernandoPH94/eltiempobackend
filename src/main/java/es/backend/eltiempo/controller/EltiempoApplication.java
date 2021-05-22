package es.backend.eltiempo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EltiempoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EltiempoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/municipios/{id}").allowedOrigins("0.0.0.0");
				registry.addMapping("/cod").allowedOrigins("0.0.0.0");
				registry.addMapping("/prueba").allowedOrigins("0.0.0.0");
			}
		};
	}

}
