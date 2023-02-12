package uniandes.dpoo.taller2.procesamiento;

import java.io.File;
import java.util.ArrayList;

import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.Producto;

public class Restaurante {
	
	private ArrayList<Pedido> pedidos;
	private ArrayList<Ingrediente> ingredientes;
	private Pedido pedidoEnCurso;
	
	public Restaurante() {
		this.pedidos = new ArrayList<Pedido>();
		this.ingredientes = new ArrayList<Ingrediente>();
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
	}

	public void cerrarYGuardarPedido() {
		this.pedidos.add(pedidoEnCurso);
		this.pedidoEnCurso = null;
	}
	
	public Pedido getPedidoEnCurso() {
		return this.pedidoEnCurso;
	}
	
	public ArrayList<Producto> getMenuBase() {
		return null;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return this.ingredientes;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {
		
	}
	
	private void cargarIngredientes(File archivoIngredientes) {
		
	}
	
	private void cargarMenu(File archivoMenu) {
		
	}
	
	private void cargarCombos(File archivoCombos) {
		
	}
}
