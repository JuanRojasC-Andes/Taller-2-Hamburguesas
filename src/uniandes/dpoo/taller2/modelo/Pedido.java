package uniandes.dpoo.taller2.modelo;

import java.io.File;
import java.util.ArrayList;

public class Pedido {

	private int idPedido;
	private int numeroPedidos;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	private double iva;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		super();
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
		this.iva = 0.19;
	}
	
	public int getIdPedido() {
		return this.idPedido;
	}
	
	public void setIdPedido(int id) {
		this.idPedido = id;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}
	
	public void guardarFactura(File archivo) {
		
	}
	
	public void consultarPedido(int idPedido) {
		
	}
	
	private double getPrecioNetoPedido() {
		return getPrecioTotalPedido() - getImpuestos();
	}
	
	private int getPrecioTotalPedido() {
		int precio = 0;
		for (Producto p : itemsPedido) {
			precio += p.getPrecio();
		}
		return precio;
	}
	
	private double getImpuestos() {
		return getPrecioTotalPedido() * iva;
	}
	
	public String generarTextoFactura() {
		String texto = "\n" + "=".repeat(20) + "\n"
				+ "\nTIENDA HAMBURGUESAS\n"
				+ "Pedido: "
				+ this.idPedido
				+ "\n\nITEM   PRECIO\n";
		for (Producto p : itemsPedido) {
			texto += "\n" + p.generarTextoFactura();
		}
		texto += "\n\n\nNETO   "
				+ getPrecioNetoPedido()
				+ "\nIVA   "
				+ getImpuestos()
				+ "\nTOTAL   "
				+ getPrecioTotalPedido()
				+ "\n\n" + "=".repeat(20);
		return texto;
	}
}
