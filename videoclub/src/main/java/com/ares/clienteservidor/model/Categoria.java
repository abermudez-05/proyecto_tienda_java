package com.ares.clienteservidor.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {

	private String nombre;
	private String descrpicion;
	
	@OneToMany(mappedBy = "categoria")
	private List<Pelicula> peliculas = new ArrayList<Pelicula>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nombre, String descrpicion) {
		super();
		this.nombre = nombre;
		this.descrpicion = descrpicion;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescrpicion() {
		return descrpicion;
	}


	public void setDescrpicion(String descrpicion) {
		this.descrpicion = descrpicion;
	}


	public List<Pelicula> getPeliculas() {
		return peliculas;
	}


	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	
}
