package com.ares.clienteservidor.constantesSQL;

public class ConstantesSQL {

	public static final String SQL_OBTENER_PELICULAS_PARA_JSON = "select p.id, p.titulo, p.director, p.descripcion, p.genero, p.precio, p.presupuesto, c.nombre as nombre_categoria from tabla_peliculas as p, categoria as c where p.categoria_id = c.id order by p.id desc";

	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = "SELECT C.USUARIO_ID, TP.TITULO, TP.ID AS PELICULA_ID, TP.PRECIO, TP.DESCRIPCION, C.CANTIDAD FROM CARRITO AS C, TABLA_PELICULAS AS TP WHERE USUARIO_ID = :id_usuario AND TP.ID = C.PELICULA_ID";

	public static final String SQL_ELIMINAR_PRODUCTO_CARRITO =
		    "DELETE FROM CARRITO WHERE PELICULA_ID = :pelicula_id AND USUARIO_ID = :usuario_id";

	public static final String SQL_VACIAR_CARRITO = "DELETE FROM CARRITO WHERE USUARIO_ID = :usuario_id";

	
}
