package app.disney.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.disney.DTO.MovieDTO;
import app.disney.service.IMovieService;

@Controller
public class moviesController {
	
	@Autowired
	private IMovieService movieService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/movies")
	public String listMovies(Model model) {
		
		List<MovieDTO> listMovieDTO = movieService.mappingListToDTO(movieService.getAllMovie());
		
		model.addAttribute("movies", listMovieDTO);
		
		
		return "movies";
		
	}
	
	
	
	
	

}
