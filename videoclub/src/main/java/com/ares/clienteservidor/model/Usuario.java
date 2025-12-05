package com.ares.clienteservidor.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

    @NotEmpty(message = "Debes insertar un nombre")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(
        regexp = "^[A-Za-z áéíóúÁÉÍÓÚñÑ]+$",
        message = "El nombre solo puede contener letras y espacios"
    )
    private String nombre;

    @NotEmpty(message = "Debes insertar una contraseña")
    @Size(min = 3, max = 20, message = "La contraseña debe tener entre 3 y 20 caracteres")
    private String pass;

    @NotEmpty(message = "Debes insertar un email")
    @Email(message = "El email debe tener un formato válido")
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Carrito> productos_carrito = new ArrayList<>();

    @NotEmpty(message = "Debes insertar un teléfono")
    @Pattern(
        regexp = "^[0-9]{9}$",
        message = "El teléfono debe tener exactamente 9 dígitos"
    )
    private String telefono;

    @NotEmpty(message = "Debes insertar un nickname")
    @Size(min = 3, max = 20, message = "El nickname debe tener entre 3 y 20 caracteres")
    @Pattern(
        regexp = "^[A-Za-z0-9]+$",
        message = "El nickname solo puede contener letras y números"
    )
    private String nickname;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nombre, String pass, String email, String telefono, String nickname) {
		super();
		this.nombre = nombre;
		this.pass = pass;
		this.email = email;
		this.telefono = telefono;
		this.nickname = nickname;
	}
	
	
	
	public List<Carrito> getProductos_carrito() {
		return productos_carrito;
	}

	public void setProductos_carrito(List<Carrito> productos_carrito) {
		this.productos_carrito = productos_carrito;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
