package com.ares.clienteservidor.controllers.loginAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ares.clienteservidor.controllers.admin.PeliculasController;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginAdminController {
	
	@Autowired
	PeliculasController peliculasController;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping("loginAdmin")
	public String loginAdmin() {
		return "admin/loginAdmin";
	}
	
//	@RequestMapping("comprobarPassAdmin")
//	public String comprobarPassAdmin(String pass, Model model, HttpServletRequest request) {
//		if(pass.equals("123")) {
//			request.getSession().setAttribute("admin","ok");
//			return peliculasController.obtenerPeliculas(model);
//		}else {
//			model.addAttribute("mensaje", "pass incorrecta");
//			return "admin/loginAdmin";
//		}
//	}
	
	@RequestMapping("logoutAdmin")
	public String logoutAdmin(HttpServletRequest request) {
		String idiomaActual = messageSource.getMessage("idioma", null, LocaleContextHolder.getLocale());
		request.getSession().invalidate();
		System.out.println("Idioma actual: " + idiomaActual);
		return "tienda_"+idiomaActual;
	}
	
}
