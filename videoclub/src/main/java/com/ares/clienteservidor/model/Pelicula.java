package com.ares.clienteservidor.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tabla_peliculas")
public class Pelicula {

	
	@Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    @NotEmpty(message = "Debes insertar un título")
    @Pattern(
        regexp = "^(?!\\s)([A-Za-z0-9 áéíóúÁÉÍÓÚñÑ:'\"\\-\\?!()]+)(?<!\\s)$",
        message = "El título solo puede contener letras, números, espacios y signos de puntuación comunes"
    )
    @Column(length = 150, nullable = false)
    private String titulo;

    @Size(min = 3, max = 50, message = "El nombre del director debe tener entre 3 y 50 caracteres")
    @NotEmpty(message = "Debes insertar un director")
    @Pattern(
        regexp = "^[A-Za-z áéíóúÁÉÍÓÚñÑ]+( [A-Za-z áéíóúÁÉÍÓÚñÑ]+)*$",
        message = "El director solo puede contener letras y espacios"
    )
    private String director;

    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    private String descripcion;

    @NotEmpty(message = "Debes insertar un género")
    @Size(min = 3, max = 50, message = "El género debe tener entre 3 y 50 caracteres")
    @Pattern(
        regexp = "^[A-Za-z áéíóúÁÉÍÓÚñÑ/\\-]+$",
        message = "El género solo puede contener letras, espacios, guiones y barras"
    )
    private String genero;

    @NotNull(message = "Debes insertar un precio")
    @Min(value = 1, message = "El precio mínimo es de 1 €")
    @Max(value = 999, message = "El precio máximo es 999 €")
    private double precio;

    @Min(value = 1, message = "El presupuesto mínimo es 1 €")
    @Max(value = 1000000, message = "El presupuesto máximo es 1.000.000 €")
    private double presupuesto;

	//campo para la imagen del producto
	@Lob
	@Column(name = "imagen_portada", columnDefinition = "LONGBLOB")
	private byte[] imagenPortada;
	
	@Lob
	@Column(name = "imagen_extendida", columnDefinition = "LONGBLOB")
	private byte[] imagenExtendida;
	
	@Transient
	private MultipartFile imagen;
	
	@Transient
	private MultipartFile imagenExtendidaFormulario;
	
	@OneToMany(mappedBy = "pelicula")
	private List<Carrito> carritos = new ArrayList<Carrito>();
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Transient
	private long idCategoria;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public Pelicula() {
		// TODO Auto-generated constructor stub
	}

	public Pelicula(long id, String titulo, String director, String descripcion, String genero, double precio, double presupuesto) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.descripcion = descripcion;
		this.genero = genero;
		this.precio = precio;
		this.presupuesto = presupuesto;
	}
	
	public Pelicula(String titulo, String director, String descripcion, String genero, double precio, double presupuesto) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.descripcion = descripcion;
		this.genero = genero;
		this.precio = precio;
		this.presupuesto = presupuesto;
	}

	
	
	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}

	public MultipartFile getImagen() {
		return imagen;
	}

	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	

	public byte[] getImagenExtendida() {
		return imagenExtendida;
	}

	public void setImagenExtendida(byte[] imagenExtendida) {
		this.imagenExtendida = imagenExtendida;
	}

	public MultipartFile getImagenExtendidaFormulario() {
		return imagenExtendidaFormulario;
	}

	public void setImagenExtendidaFormulario(MultipartFile imagenExtendidaFormulario) {
		this.imagenExtendidaFormulario = imagenExtendidaFormulario;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", director=" + director + ", descripcion=" + descripcion
				+ ", genero=" + genero + ", precio=" + precio + ", presupuesto=" + presupuesto + "]";
	}
	
	
}
