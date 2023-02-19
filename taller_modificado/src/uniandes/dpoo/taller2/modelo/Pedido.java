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

	private static Integer numeroPedidos = 0;
	private static Double iva = 0.19;
	
	private Integer idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		super();
		this.idPedido = numeroPedidos + 1;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
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
	
	public Integer getIdPedido() {
		return this.idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		this.itemsPedido.add(nuevoItem);
	}
	
	private Double getPrecioNetoPedido() {
		return getPrecioTotalPedido() - getPrecioIVAPedido();
	}
	
	private Integer getPrecioTotalPedido() {
		Integer precio = 0;
		for (Producto p : itemsPedido) {
			precio += p.getPrecio();
		}
		return precio;
	}
	
	private Double getPrecioIVAPedido() {
		return getPrecioTotalPedido() * iva;
	}
	
	private Integer getCaloriasPedido() {
		Integer calorias = 0;
		for(Producto p : itemsPedido) {
			calorias += p.getCalorias();
		}
		return calorias;
	}
	
	private String generarTextoFactura() {
		String texto = "\n" + "=".repeat(20) + "\n"
				+ "\nTIENDA HAMBURGUESAS\n"
				+ "Pedido: "
				+ this.idPedido
				+ "\nCliente: "
				+ this.nombreCliente
				+ "\nDireccion: "
				+ this.direccionCliente
				+ "\n\nITEM   PRECIO\n";
		for (Producto p : itemsPedido) {
			texto += "\n" + p.generarTextoFactura();
		}
		texto += "\n\n\nNETO   "
				+ getPrecioNetoPedido()
				+ "\nIVA   "
				+ getPrecioIVAPedido()
				+ "\nTOTAL   "
				+ getPrecioTotalPedido()
				+ "\n\nTu pedido te aportara "
				+ getCaloriasPedido()
				+ " calorias"
				+ "\n\n" + "=".repeat(20);
		return texto;
	}
	
	public void guardarFactura(File archivo) throws IOException {
		String factura = generarTextoFactura();
		System.out.println(factura);
		GestorDeArchivos.guardarArchivo(archivo.getAbsolutePath(), factura);
	}
}
