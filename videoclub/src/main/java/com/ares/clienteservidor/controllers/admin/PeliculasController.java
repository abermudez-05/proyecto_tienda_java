package com.ares.clienteservidor.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ares.clienteservidor.model.Pelicula;
import com.ares.clienteservidor.servicios.ServicioCategorias;
import com.ares.clienteservidor.servicios.ServicioPeliculas;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/")
public class PeliculasController {

	@Autowired
	private ServicioPeliculas servicioPeliculas;
	
	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("editarPelicula")
	public String editarPelicula(@RequestParam("id") Long id, Model model) {
		Pelicula pelicula = servicioPeliculas.obtenerPeliculaPorId(id);
		pelicula.setIdCategoria(pelicula.getCategoria().getId());
		model.addAttribute("peliculaEditar",pelicula);
		model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
		return "admin/peliculas_editar";
	}
	
	@RequestMapping("guardarCambiosPelicula")
	public String guardarCambiosPelicula(@ModelAttribute("peliculaEditar") @Valid Pelicula peliculaEditar, BindingResult resultadoValidaciones, Model model) {    
	    if (resultadoValidaciones.hasErrors()) {
	        return "admin/peliculas_editar";
	    }
	    servicioPeliculas.actualizarPelicula(peliculaEditar);
	    return obtenerPeliculas(model);
	}

	
	@RequestMapping("registrarPelicula")
	public String registrarPelicula(Model model) {
		Pelicula p = new Pelicula();
		p.setPrecio(1);
		model.addAttribute("nuevoPelicula", p);
		//vamos a meter las categorias en model para que le lleguen a la vista
		model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
		return "admin/peliculas_registro";
	}
	
	@RequestMapping("guardarPelicula")
	public String guardarPelicula(@ModelAttribute("nuevoPelicula") @Valid Pelicula nuevoPelicula, BindingResult resultadoValidaciones, Model model) {
		if(resultadoValidaciones.hasErrors()) {
			model.addAttribute("categorias", servicioCategorias.obtenerCategorias());
			return "admin/peliculas_registro";
		}
		servicioPeliculas.registrarPelicula(nuevoPelicula);
		return obtenerPeliculas(model);
	}
	
	
	@RequestMapping("obtenerPeliculas")
	public String obtenerPeliculas(Model model) {
		model.addAttribute("peliculas", servicioPeliculas.obtenerPeliculas());
		return "admin/peliculas";
	}
	
	@RequestMapping("borrarPelicula")
	public String borrarPelicula(@RequestParam("id") Long id, Model model) {
		servicioPeliculas.borrarPelicula(id);
		return obtenerPeliculas(model);
	}
	
	
}
