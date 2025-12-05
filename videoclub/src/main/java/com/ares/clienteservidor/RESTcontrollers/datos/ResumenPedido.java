package com.ares.clienteservidor.RESTcontrollers.datos;

import java.util.List;
import java.util.Map;

public class ResumenPedido {

	//productos que hay en el carrito
	
	private List<Map<String, Object>> peliculas;
	
	//datos del paso1
	private String nombreCompleto;
	private String direccion;
	private String provincia;
	private String postal;
	private String telefono;
	private String observaciones;
	
	//datos del paso2
	private String titularTarjeta;
	private String numeroTarjeta;
	private String tipoTarjeta;
	
	//datos del paso3
	private String generoFavorito;
	private String peliculaFavorita;
	
	public List<Map<String, Object>> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Map<String, Object>> peliculas) {
		this.peliculas = peliculas;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getGeneroFavorito() {
		return generoFavorito;
	}
	public void setGeneroFavorito(String generoFavorito) {
		this.generoFavorito = generoFavorito;
	}
	public String getPeliculaFavorita() {
		return peliculaFavorita;
	}
	public void setPeliculaFavorita(String peliculaFavorita) {
		this.peliculaFavorita = peliculaFavorita;
	}
		
}
