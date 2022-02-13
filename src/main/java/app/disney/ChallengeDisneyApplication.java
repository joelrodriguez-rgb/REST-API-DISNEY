package app.disney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import app.disney.entitys.AppRole;
import app.disney.repository.IGenderRepository;
import app.disney.repository.IMovieRepository;
import app.disney.repository.IPersonajeRepository;
import app.disney.repository.IRoleRepository;

@SpringBootApplication
@Transactional
public class ChallengeDisneyApplication implements CommandLineRunner {
	
	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private IMovieRepository movieRepo;

	@Autowired
	private IGenderRepository genderRepo;
	
	@Autowired
	private IRoleRepository rolRepo;
	

	public static void main(String[] args) {
		SpringApplication.run(ChallengeDisneyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Gender gen1 = new Gender("dibujo");
//		Gender gen2 = new Gender("animado");
//		Gender gen3 = new Gender("terror");
//
//		genderRepo.save(gen1);
//		genderRepo.save(gen2);
//		genderRepo.save(gen3);

//		LocalDate date1 = LocalDate.of(2003, 6, 20);
//		Movie mov1 = new Movie("la Bella y la Bestia", date1, 3, genderRepo.findByGenderName("dibujo"));
//		movieRepo.save(mov1);
//
//		LocalDate date2 = LocalDate.of(2008, 5, 4);
//		Movie mov2 = new Movie("la Bella y la Bestia 2", date2, 5, genderRepo.findByGenderName("dibujo"));
//		movieRepo.save(mov2);
//
//		LocalDate date3 = LocalDate.of(2015, 9, 15);
//		Movie mov3 = new Movie("la lampara de Aladin", date3, 5, genderRepo.findByGenderName("animado"));
//		movieRepo.save(mov3);

		
		
		
		
		
//		List<Movie> listMos = new ArrayList<>();
//		listMos.add(movieRepo.findByTitleIgnoreCase("la bella y la bestia"));
//		listMos.add(movieRepo.findByTitleIgnoreCase("la bella y la bestia 2"));
//
//		Personaje bestia = new Personaje("Bestia",35, 120, listMos);
//		personajeRepo.save(bestia);
//
//		Personaje bella = new Personaje("Bella", 25, 60, listMos);
//		personajeRepo.save(bella);
//
//		Personaje aladin = new Personaje("Aladin2", 35, 60,  movieRepo.findByTitleIgnoreCase("la lampara de aladin 2"));
//		personajeRepo.save(aladin);
//		
//		AppRole rol = new AppRole("USUARIO");
//		
//		rolRepo.save(rol);
//		
		

	}

}
