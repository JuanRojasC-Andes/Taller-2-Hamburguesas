package uniandes.dpoo.taller2.modelo;

import java.io.File;
import java.util.ArrayList;

public class Pedido {

	private int idPedido;
	private int numeroPedidos;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		super();
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
	}
	
	public int getIdPedido() {
		return this.idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}
	
	public void guardarFactura(File archivo) {
		
	}
	
	public void consultarPedido(int idPedido) {
		
	}
	
	private int getPrecioNetoPrdido() {
		return 0;
	}
	
	private int getPrecioTotalPedido() {
		return 0;
	}
	
	private String generarTextoFactura() {
		return null;
	}
}
