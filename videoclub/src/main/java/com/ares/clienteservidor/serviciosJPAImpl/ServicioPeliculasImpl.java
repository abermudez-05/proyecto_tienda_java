package com.ares.clienteservidor.serviciosJPAImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;

import com.ares.clienteservidor.constantesSQL.ConstantesSQL;
import com.ares.clienteservidor.model.Categoria;
import com.ares.clienteservidor.model.Pelicula;
import com.ares.clienteservidor.servicios.ServicioPeliculas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioPeliculasImpl implements ServicioPeliculas{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registrarPelicula(Pelicula pelicula) {
		try {
			pelicula.setImagenPortada(pelicula.getImagen().getBytes());
			pelicula.setImagenExtendida(pelicula.getImagenExtendidaFormulario().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//tenemos que asociar la categoria a nivel de base de datos porque idCategoria tiene puesto @Transient
		Categoria categoria = entityManager.find(Categoria.class, pelicula.getIdCategoria());
		pelicula.setCategoria(categoria);
		entityManager.persist(pelicula);
	}

	@Override
	public List<Pelicula> obtenerPeliculas() {
		return entityManager.createQuery("select p from Pelicula p").getResultList();

	}

	@Override
	public void borrarPelicula(long id) {
		entityManager.createNativeQuery("delete from CARRITO where PELICULA_ID = :id ").setParameter("id", id).executeUpdate();

		entityManager.createNativeQuery("delete from PRODUCTO_PEDIDO where PELICULA_ID = :id ").setParameter("id", id).executeUpdate();

		entityManager.createNativeQuery("delete from tabla_peliculas where id = :id ").setParameter("id", id).executeUpdate();
	}

	@Override
	public void actualizarPelicula(Pelicula peliculaEditar) {
	    Pelicula pelicula = entityManager.find(Pelicula.class, peliculaEditar.getId());

	    pelicula.setTitulo(peliculaEditar.getTitulo());
	    pelicula.setDirector(peliculaEditar.getDirector());
	    pelicula.setDescripcion(peliculaEditar.getDescripcion());
	    pelicula.setGenero(peliculaEditar.getGenero());
	    pelicula.setPrecio(peliculaEditar.getPrecio());
	    pelicula.setPresupuesto(peliculaEditar.getPresupuesto());

	    // Comprobar si se sube una nueva imagen
	    if (peliculaEditar.getImagen() != null && !peliculaEditar.getImagen().isEmpty()) {
	        try {
	            pelicula.setImagenPortada(peliculaEditar.getImagen().getBytes());
	        } catch (IOException e) {
	            System.out.println("No se pudo procesar el archivo subido");
	            e.printStackTrace();
	        }
	    }
	    if (peliculaEditar.getImagenExtendidaFormulario() != null && !peliculaEditar.getImagenExtendidaFormulario().isEmpty()) {
	        try {
	            pelicula.setImagenExtendida(peliculaEditar.getImagenExtendidaFormulario().getBytes());
	        } catch (IOException e) {
	            System.out.println("No se pudo procesar el archivo subido");
	            e.printStackTrace();
	        }
	    }
	    pelicula.setCategoria(entityManager.find(Categoria.class, peliculaEditar.getIdCategoria()));
	    entityManager.merge(pelicula);
	}


	@Override
	public Pelicula obtenerPeliculaPorId(long id) {
		return entityManager.find(Pelicula.class, id);
	}

	@Override
	public List<Map<String, Object>> obtenerPeliculasParaFormarJSON() {
	    Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_PELICULAS_PARA_JSON, Tuple.class);
	    List<Tuple> tuples = query.getResultList();
	    List<Map<String, Object>> resultado = new ArrayList<>();
	    for (Tuple tuple : tuples) {
	    	Map<String, Object> fila = new HashMap<>();
	    	for (TupleElement<?> element : tuple.getElements()) {
	    		fila.put(element.getAlias(), tuple.get(element));
	    	}
	    	resultado.add(fila);
	    }
	    return resultado;
	}

	
	
	
}
