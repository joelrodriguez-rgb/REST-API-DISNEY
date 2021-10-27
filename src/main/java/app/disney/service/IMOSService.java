package app.disney.service;

import java.util.List;


import app.disney.entitys.MovieOrSerie;

public interface IMOSService {
	
	/* FUNCIONES CRUD */
	List<MovieOrSerie> getAllMos();

	MovieOrSerie saveMos(MovieOrSerie mos);

	MovieOrSerie getMosById(Integer id);

	void deleteMosById(Integer id);
	
	/* BUSQUEDAS */
		
	
	/*FILTROS*/
	

}
