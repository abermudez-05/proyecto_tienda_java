package com.ares.clienteservidor.setup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ares.clienteservidor.model.Carrito;
import com.ares.clienteservidor.model.Categoria;
import com.ares.clienteservidor.model.Pelicula;
import com.ares.clienteservidor.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class SetUpImpl implements SetUp{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void prepararRegistro() {
		TablaSetUp registroSetUp = null;
		//si no hay un registro en la tabla setup preparamos los registros iniciales
		try {
			registroSetUp = (TablaSetUp) entityManager.createQuery("select r from TablaSetUp r").getSingleResult();
		} catch (Exception e) {
			System.out.println("no se encontro ningun registro en la tabla setup, comenzamos a realizar los registros iniciales...");
		}
		if(registroSetUp!=null && registroSetUp.isCompletado()) {
			return;
		}
		
		// Categorías creadas previamente
		Categoria cAccion = new Categoria("Acción", "Películas llenas de adrenalina, combates y persecuciones");
		entityManager.persist(cAccion);

		Categoria cAventura = new Categoria("Aventura", "Películas de exploración, viajes y mundos fantásticos");
		entityManager.persist(cAventura);

		Categoria cCienciaFiccion = new Categoria("Ciencia Ficción", "Historias futuristas, tecnología avanzada y universos alternativos");
		entityManager.persist(cCienciaFiccion);

		Categoria cFantasia = new Categoria("Fantasía", "Películas con magia, criaturas míticas y mundos imaginarios");
		entityManager.persist(cFantasia);


		// Películas con categoría asignada
		// Películas con categoría asignada
		Pelicula pelicula1 = new Pelicula("El Señor de los Anillos: La Comunidad del Anillo", "Peter Jackson", "Un grupo de héroes inicia un viaje épico para destruir un anillo maligno que amenaza con sumergir al mundo en oscuridad eterna.", "Aventura", 50, 200);
		pelicula1.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/comunidad_del_anillo.jpg"));
		pelicula1.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/comunidad_del_anillo.jpg"));
		pelicula1.setCategoria(cFantasia);
		entityManager.persist(pelicula1);

		Pelicula pelicula2 = new Pelicula("Avatar", "James Cameron", "Un ex marine llega al planeta Pandora y se ve envuelto en un conflicto que lo obliga a elegir entre dos mundos distintos.", "Ciencia Ficción", 60, 250);
		pelicula2.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/avatar.jpg"));
		pelicula2.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/avatar.jpg"));
		pelicula2.setCategoria(cCienciaFiccion);
		entityManager.persist(pelicula2);

		Pelicula pelicula3 = new Pelicula("Interestelar", "Christopher Nolan", "Un equipo de astronautas cruza un agujero de gusano buscando un nuevo hogar para la humanidad ante la inminente destrucción de la Tierra.", "Ciencia Ficción", 70, 280);
		pelicula3.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/interestelar.jpg"));
		pelicula3.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/interestelar.jpg"));
		pelicula3.setCategoria(cCienciaFiccion);
		entityManager.persist(pelicula3);

		Pelicula pelicula4 = new Pelicula("Inception (El Origen)", "Christopher Nolan", "Un ladrón experto en infiltrarse en los sueños acepta una misión imposible que pone a prueba su mente y sus recuerdos más profundos.", "Ciencia Ficción", 65, 220);
		pelicula4.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/inception.jpg"));
		pelicula4.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/inception.jpg"));
		pelicula4.setCategoria(cCienciaFiccion);
		entityManager.persist(pelicula4);

		Pelicula pelicula5 = new Pelicula("Jurassic Park", "Steven Spielberg", "Un parque con dinosaurios clonados se convierte en un caos cuando las criaturas escapan y amenazan a todos los visitantes del recinto.", "Aventura", 55, 210);
		pelicula5.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/jurassic_park.jpg"));
		pelicula5.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/jurassic_park.jpg"));
		pelicula5.setCategoria(cAventura);
		entityManager.persist(pelicula5);

		Pelicula pelicula6 = new Pelicula("Star Wars: Episodio IV - Una Nueva Esperanza", "George Lucas", "Un joven granjero se une a la rebelión para enfrentarse al Imperio Galáctico y ayudar a restaurar la libertad en la galaxia.", "Ciencia Ficción", 50, 190);
		pelicula6.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/star_wars_nueva_esperanza.jpg"));
		pelicula6.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/star_wars_nueva_esperanza.jpg"));
		pelicula6.setCategoria(cCienciaFiccion);
		entityManager.persist(pelicula6);

		Pelicula pelicula7 = new Pelicula("Guardianes de la Galaxia", "James Gunn", "Un grupo de inadaptados intergalácticos se ve obligado a trabajar unido para detener una amenaza que podría destruir todo el universo.", "Acción / Ciencia Ficción", 60, 210);
		pelicula7.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/guardianes_galaxia.jpg"));
		pelicula7.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/guardianes_galaxia.jpg"));
		pelicula7.setCategoria(cAccion);
		entityManager.persist(pelicula7);

		Pelicula pelicula8 = new Pelicula("Matrix", "Lana y Lilly Wachowski", "Un hacker descubre que su realidad es una simulación controlada por máquinas y se une a la resistencia para liberar a la humanidad.", "Ciencia Ficción", 65, 220);
		pelicula8.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/matrix.jpg"));
		pelicula8.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/matrix.jpg"));
		pelicula8.setCategoria(cCienciaFiccion);
		entityManager.persist(pelicula8);

		Pelicula pelicula9 = new Pelicula("Piratas del Caribe: La Maldición del Perla Negra", "Gore Verbinski", "El capitán Jack Sparrow busca recuperar su barco perdido mientras enfrenta a una tripulación maldita atrapada entre la vida y la muerte.", "Aventura", 55, 215);
		pelicula9.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/piratas_perla_negra.jpg"));
		pelicula9.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/piratas_perla_negra.jpg"));
		pelicula9.setCategoria(cAventura);
		entityManager.persist(pelicula9);

		Pelicula pelicula10 = new Pelicula("Spider-Man: No Way Home", "Jon Watts", "Tras un hechizo fallido, Peter Parker enfrenta enemigos de otros universos y lucha por proteger a quienes ama sin perder su identidad.", "Acción / Ciencia Ficción", 70, 230);
		pelicula10.setImagenPortada(obtenerInfoImagen("http://localhost:8080/imagenes_base/peliculas/spiderman_no_way_home.png"));
		pelicula10.setImagenExtendida(obtenerInfoImagen("http://localhost:8080/imagenes_extendidas/peliculas/spiderman_no_way_home.png"));
		pelicula10.setCategoria(cAccion);
		entityManager.persist(pelicula10);




		Usuario usuario1 = new Usuario("Alberto", "123", "abermudez@centronelson.org", "654987456", "abermudez");
		entityManager.persist(usuario1);
		
		Usuario usuario2 = new Usuario("María", "456", "mrodriguez@centronelson.org", "698745123", "mrodriguez");
		entityManager.persist(usuario2);

		Usuario usuario3 = new Usuario("Carlos", "789", "cfernandez@centronelson.org", "612345987", "cfernandez");
		entityManager.persist(usuario3);

		Usuario usuario4 = new Usuario("Lucía", "abc", "llopez@centronelson.org", "634512789", "llopez");
		entityManager.persist(usuario4);

		Usuario usuario5 = new Usuario("Javier", "def", "jgomez@centronelson.org", "676543219", "jgomez");
		entityManager.persist(usuario5);

		Usuario usuario6 = new Usuario("Elena", "ghi", "emartin@centronelson.org", "689123456", "emartin");
		entityManager.persist(usuario6);


		Carrito registroCarrito = new Carrito();
		registroCarrito.setUsuario(usuario1);
		registroCarrito.setPelicula(pelicula1);
		registroCarrito.setCantidad(3);
		entityManager.persist(registroCarrito);
		
		Carrito registroCarrito2 = new Carrito();
		registroCarrito2.setUsuario(usuario5);
		registroCarrito2.setPelicula(pelicula3);
		registroCarrito2.setCantidad(9);
		entityManager.persist(registroCarrito2);
		
		//una vez preparados los registros iniciales marcamos que se ha completado de la siguiente forma
		TablaSetUp registro = new TablaSetUp();
		registro.setCompletado(true);
		entityManager.persist(registro);

	}//end prepararRegistro
	
	//metodo que nos va a permitir obtener un byte[] de cada archivo de imagens_base
	private byte[] obtenerInfoImagen(String ruta_origen) {
		byte[] info = null;
		try {
			URL url = new URL(ruta_origen);
			info = IOUtils.toByteArray(url);
		} catch (IOException e) {
			System.out.println("No se pudo procesar " + ruta_origen);
			e.printStackTrace();
		}
		return info;
	}

}
