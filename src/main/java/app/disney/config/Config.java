package app.disney.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.disney.ports.input.rs.request.SearchPersonajeDTO;
import app.disney.util.IMapper;
import app.disney.util.Mapping;

@Configuration
public class Config {
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public SearchPersonajeDTO searchPersonajeDTO() {
		return new SearchPersonajeDTO();
	}
	
	@Bean
	public IMapper mapping() {
		
		return new Mapping();
	}

}
