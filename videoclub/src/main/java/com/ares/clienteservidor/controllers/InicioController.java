package com.ares.clienteservidor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ares.clienteservidor.setup.SetUp;

/* controlador que atiende la ruta de inicio y muestra 
 * la vista tienda.html */

@Controller
public class InicioController {

	@Autowired
	private SetUp setUp;
	
	@Autowired
	private MessageSource messageSource;
	
	//este request mapping vacio, atiende la ruta por defecto de la aplicacion
	@RequestMapping()
	public String inicio() {
		//aprovechamos y llamamos a la operacion para hacer uso del setup inicial
		setUp.prepararRegistro();
		String idiomaActual = messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		System.out.println("Idioma actual: " + idiomaActual);
		return "tienda_"+idiomaActual;
	}
	
}
