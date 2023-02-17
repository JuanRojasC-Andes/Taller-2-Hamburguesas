package uniandes.dpoo.taller2.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import uniandes.dpoo.taller2.procesamiento.GestorDeArchivos;

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
	
	public static void consultarPedido(int idPedido) {
		String path = String.format("./data/%d.txt", idPedido);
		try {
			BufferedReader archivo = GestorDeArchivos.cargarArchivoBuffered(path);
			String pedido = archivo.lines().collect(Collectors.joining("\n"));
			System.out.println("Hemos encontrado la factura de tu pedido:");
			System.out.println(pedido);
		} catch(Exception e) {
			System.out.println("Lo sentimos tu pedido no fue encontrado!");
		}
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
	
	public void guardarFactura(File archivo) throws IOException {
		String factura = generarTextoFactura();
		System.out.println(factura);
		GestorDeArchivos.guardarArchivo(archivo.getAbsolutePath(), factura);
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
	
	private String generarTextoFactura() {
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
