package com.ares.clienteservidor.servicios;

import java.util.List;
import java.util.Map;

import com.ares.clienteservidor.model.Pelicula;


public interface ServicioPeliculas {
	
	void registrarPelicula(Pelicula pelicula);
	
	List<Pelicula> obtenerPeliculas();
	
	void borrarPelicula(long id);
	
	void actualizarPelicula(Pelicula pelicula);

	Pelicula obtenerPeliculaPorId(long id);
	
	List< Map<String, Object> > obtenerPeliculasParaFormarJSON();

}
