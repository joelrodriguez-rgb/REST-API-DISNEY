package app.disney.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.disney.DTO.SearchPersonajeDTO;

@Configuration
public class Config {
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public SearchPersonajeDTO SearchPersonajeDTO() {
		return new SearchPersonajeDTO();
	}

}
