package app.disney.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.disney.entitys.MovieOrSerie;
import app.disney.repository.IMosRepository;
import app.disney.service.IMOSService;

@Service
public class MosServiceImplemet implements IMOSService {

	@Autowired
	private IMosRepository mosRepository;

	/* FUNCIONES CRUD */
	@Override
	public List<MovieOrSerie> getAllMos() {
		return mosRepository.findAll();
	}

	@Override
	public MovieOrSerie saveMos(MovieOrSerie mos) {
		return mosRepository.save(mos);
	}

	@Override
	public MovieOrSerie getMosById(Integer id) {
		return mosRepository.findById(id).get();
	}

	@Override
	public void deleteMosById(Integer id) {
		mosRepository.deleteById(id);
	}


	/* BUSQUEDAS */

//	@Override
//	public MovieOrSerie getByPersonageName(String characterName) {
//		return mosRepository.findByPersonageName(characterName);
//	}
}
