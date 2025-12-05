package com.ares.clienteservidor.controllers.imagen;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ares.clienteservidor.servicios.ServicioPeliculas;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MostrarImagenProducto {
	
	@Autowired
	private ServicioPeliculas servicioPeliculas;
	
	@RequestMapping("mostrar_imagen")
	public void mostrarImagen(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
		byte[] info = servicioPeliculas.obtenerPeliculaPorId(id).getImagenPortada();
		if(info == null) {
			return;
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
	
	@RequestMapping("mostrar_imagen_extendida")
	public void mostrarImagenExtendida(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
		byte[] info = servicioPeliculas.obtenerPeliculaPorId(id).getImagenExtendida();
		if(info == null) {
			return;
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}

}
