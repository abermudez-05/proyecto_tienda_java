package com.ares.clienteservidor.RESTcontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ares.clienteservidor.model.Pelicula;
import com.ares.clienteservidor.servicios.ServicioPeliculas;

@RestController
@RequestMapping("peliculasREST/")
public class PeliculasREST {
	
	@Autowired
	private ServicioPeliculas servicioPeliculas;

	@RequestMapping("obtener")
	public List<Map<String, Object>>  obtenerPeliculas() {		
		return servicioPeliculas.obtenerPeliculasParaFormarJSON();
	}
	
}
