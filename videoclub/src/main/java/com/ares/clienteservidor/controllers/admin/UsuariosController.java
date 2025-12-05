package com.ares.clienteservidor.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ares.clienteservidor.model.Usuario;
import com.ares.clienteservidor.servicios.ServicioUsuarios;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin/")
public class UsuariosController {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @RequestMapping("obtenerUsuarios")
    public String obtenerUsuarios(Model model) {
        model.addAttribute("usuarios", servicioUsuarios.obtenerUsuarios());
        return "admin/usuarios";
    }

    @RequestMapping("registrarUsuario")
    public String registrarUsuario(Model model) {
        Usuario u = new Usuario();
        model.addAttribute("nuevoUsuario", u);
        return "admin/usuarios_registro";
    }

    @RequestMapping("guardarUsuario")
    public String guardarUsuario(
            @ModelAttribute("nuevoUsuario") @Valid Usuario nuevoUsuario,
            BindingResult resultadoValidaciones,
            Model model) {

        if (resultadoValidaciones.hasErrors()) {
            return "admin/usuarios_registro";
        }

        servicioUsuarios.registrarUsuario(nuevoUsuario);
        return obtenerUsuarios(model);
    }

    @RequestMapping("editarUsuario")
    public String editarUsuario(@RequestParam("id") int id, Model model) {
        Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(id);
        model.addAttribute("usuarioEditar", usuario);
        return "admin/usuarios_editar";
    }

    @RequestMapping("guardarCambiosUsuario")
    public String guardarCambiosUsuario(
            @ModelAttribute("usuarioEditar") @Valid Usuario usuarioEditar,
            BindingResult resultadoValidaciones,
            Model model) {

        if (resultadoValidaciones.hasErrors()) {
            return "admin/usuarios_editar";
        }

        servicioUsuarios.actualizarUsuario(usuarioEditar);
        return obtenerUsuarios(model);
    }

    @RequestMapping("borrarUsuario")
    public String borrarUsuario(@RequestParam("id") int id, Model model) {
        servicioUsuarios.borrarUsuario(id);
        return obtenerUsuarios(model);
    }
}
