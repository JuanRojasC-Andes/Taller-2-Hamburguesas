package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.modelo.ProductoAjustado;
import uniandes.dpoo.taller2.modelo.ProductoMenu;

public class Restaurante {
	
	private Map<Integer, Pedido> pedidos;
	private Map<Integer, Ingrediente> ingredientes;
	private Map<Integer, Producto> menuBase;
	private Map<Integer, Combo> combos;
	private int lastProductIdAvalaible;
	private Pedido pedidoEnCurso;
	
	public Restaurante() {
		this.pedidos = new HashMap<Integer, Pedido>();
		this.ingredientes = new HashMap<Integer, Ingrediente>();
		this.menuBase = new HashMap<Integer, Producto>();
		this.combos = new HashMap<Integer, Combo>();
		this.lastProductIdAvalaible = 1;
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente, ArrayList<Integer> combos, ArrayList<Integer> productos, ArrayList<Integer> adiciones, ArrayList<Integer> ingredientesRemovidos) {
		this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		agregarItemsPedido(combos, productos, adiciones, ingredientesRemovidos);
	}
	
	public void modificarPedido(ArrayList<Integer> combos, ArrayList<Integer> productos, ArrayList<Integer> adiciones, ArrayList<Integer> ingredientesRemovidos) {
		agregarItemsPedido(combos, productos, adiciones, ingredientesRemovidos);
	}
	
	private void agregarItemsPedido(ArrayList<Integer> combos, ArrayList<Integer> productos, ArrayList<Integer> adiciones, ArrayList<Integer> ingredientesRemovidos) {
		for (Integer id : combos) {
			Combo combo = this.combos.get(id);
			this.pedidoEnCurso.agregarProducto(combo);
		}	
		for (Integer id : productos) {
			Producto p = this.menuBase.get(id);
			if (adiciones.size() > 0 || ingredientesRemovidos.size() > 0) {
				ProductoAjustado pa = new ProductoAjustado((ProductoMenu) p);
				for (Integer ida : adiciones) {
					Ingrediente adicion = this.ingredientes.get(ida);
					pa.agregarIngrediente(adicion);
				}
				for (Integer idr : ingredientesRemovidos) {
					Ingrediente remover = this.ingredientes.get(idr);
					pa.eliminarIngrediente(remover);
				}
				this.pedidoEnCurso.agregarProducto(pa);
				continue;
			}
			this.pedidoEnCurso.agregarProducto(p);
		}
	}

	public void cerrarYGuardarPedido() throws IOException {
		Integer id = lastId() + 183748;
		String file = String.format("./data/%d.txt", id);
		File factura = new File(file);
		this.pedidoEnCurso.setIdPedido(id);
		this.pedidoEnCurso.guardarFactura(factura);
		this.pedidos.put(id, pedidoEnCurso);
		this.pedidoEnCurso = null;
	}
	
	public void consultarPedido(int idPedido) {
		Pedido.consultarPedido(idPedido);
	}
	
	public Pedido getPedidoEnCurso() {
		return this.pedidoEnCurso;
	}
	
	public Map<Integer, Producto> getMenuBase() {
		return this.menuBase;
	}
	
	public Map<Integer, Ingrediente> getIngredientes() {
		return this.ingredientes;
	}
	
	public Map<Integer, Combo> getCombos() {
		return this.combos;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException {
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarIngredientes(archivoIngredientes);
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		while (linea != null) {
			String[] info = linea.split(";");
			int precio = Integer.parseInt(info[1]);
			Ingrediente ingrediente = new Ingrediente(info[0], precio);
			this.ingredientes.put(lastId(), ingrediente);
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
			this.menuBase.put(lastId(), producto);
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
			for (Producto p : this.menuBase.values()) {
				for (String item: items) {
					if (item.equals(p.getNombre())) {
						combo.agregarItemACombo(p);
					}
				}
			}
			this.combos.put(lastId(), combo);
			linea = br.readLine();
		}
		br.close();
	}
	
	private int lastId() {
		int id = this.lastProductIdAvalaible;
		this.lastProductIdAvalaible += 1;
		return id;
	}
}
