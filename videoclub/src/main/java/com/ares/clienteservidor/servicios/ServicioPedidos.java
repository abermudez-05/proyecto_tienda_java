package com.ares.clienteservidor.servicios;

import java.util.List;

import com.ares.clienteservidor.RESTcontrollers.datos.ResumenPedido;
import com.ares.clienteservidor.model.Pedido;

public interface ServicioPedidos {

	//gestion administracion
	List<Pedido> obtenerPedidos();
	
	Pedido obtenerPedidoPorId(int idPedido);
	
	void actualizarPedido(int idPedido, String estado);
	
	//operaciones ajax
	void procesarPaso1(String nombre, String direccion, String provincia, String postal, String telefono, String observaciones, int idUsuario);

	void procesarPaso2(String tarjeta, String numero, String titular, int idUsuario);
	
	ResumenPedido procesarPaso3(String generoFavorito, String peliculaFavorita, int idUsuario);

	void confirmarPedido(int idUsuario);


	
	
	
}
