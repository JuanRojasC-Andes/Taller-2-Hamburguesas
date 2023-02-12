package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.modelo.ProductoMenu;

public class Restaurante {
	
	private ArrayList<Pedido> pedidos;
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Producto> menuBase;
	private ArrayList<Combo> combos;
	private Pedido pedidoEnCurso;
	
	public Restaurante() {
		this.pedidos = new ArrayList<Pedido>();
		this.ingredientes = new ArrayList<Ingrediente>();
		this.menuBase = new ArrayList<Producto>();
		this.combos = new ArrayList<Combo>();
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
		return this.menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return this.ingredientes;
	}
	
	public ArrayList<Combo> getCombos() {
		return this.combos;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		while (linea != null) {
			String[] info = linea.split(";");
			int precio = Integer.parseInt(info[1]);
			Ingrediente ingrediente = new Ingrediente(info[0], precio);
			this.ingredientes.add(ingrediente);
			linea = br.readLine();
		}
		br.close();
	}
	
	private void cargarMenu(File archivoMenu) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine();
		while (linea != null) {
			String[] info = linea.split(";");
			int precio = Integer.parseInt(info[1]);
			Producto producto = new ProductoMenu(info[0], precio);
			this.menuBase.add(producto);
			linea = br.readLine();
		}
		br.close();
	}
	
	private void cargarCombos(File archivoCombos) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine();
		while (linea != null) {
			String[] info = linea.split(";");
			double descuento = (Integer.parseInt(info[1].split("%")[0]) / 100.0);
			String[] items = Arrays.copyOfRange(info, 2, info.length);
			Combo combo = new Combo(descuento, info[0]);
			for (Producto p : this.menuBase) {
				for (String item: items) {
					if (item.equals(p.getNombre())) {
						combo.agregarItemACombo(p);
					}
				}
			}
			this.combos.add(combo);
			linea = br.readLine();
		}
		br.close();
	}
}
