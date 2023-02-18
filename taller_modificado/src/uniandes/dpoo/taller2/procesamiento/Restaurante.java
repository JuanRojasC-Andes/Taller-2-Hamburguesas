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

import uniandes.dpoo.taller2.modelo.Bebida;
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
	private Map<Integer, Bebida> bebidas;
	private Integer ultimoIdDisponible;
	private Pedido pedidoEnCurso;
	
	public Restaurante() {
		this.pedidos = new HashMap<Integer, Pedido>();
		this.ingredientes = new HashMap<Integer, Ingrediente>();
		this.menuBase = new HashMap<Integer, Producto>();
		this.combos = new HashMap<Integer, Combo>();
		this.bebidas = new HashMap<Integer, Bebida>();
		this.ultimoIdDisponible = 1;
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente, ArrayList<Integer> combos, ArrayList<Integer> productos, ArrayList<Integer> adiciones, ArrayList<Integer> ingredientesRemovidos, ArrayList<Integer> bebidas) {
		this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		agregarItemsPedido(combos, productos, adiciones, ingredientesRemovidos, bebidas);
	}
	
	public void modificarPedido(ArrayList<Integer> combos, ArrayList<Integer> productos, ArrayList<Integer> adiciones, ArrayList<Integer> ingredientesRemovidos, ArrayList<Integer> bebidas) {
		agregarItemsPedido(combos, productos, adiciones, ingredientesRemovidos, bebidas);
	}
	
	private void agregarItemsPedido(ArrayList<Integer> combos, ArrayList<Integer> productos, ArrayList<Integer> adiciones, ArrayList<Integer> ingredientesRemovidos, ArrayList<Integer> bebidas) {
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
		for (Integer id : bebidas) {
			Bebida bebida = this.bebidas.get(id);
			this.pedidoEnCurso.agregarProducto(bebida);
		}
	}

	public void cerrarYGuardarPedido() throws IOException {
		String file = String.format("./data/%d.txt", this.pedidoEnCurso.getIdPedido());
		File factura = new File(file);
		this.pedidoEnCurso.guardarFactura(factura);
		this.pedidos.put(this.pedidoEnCurso.getIdPedido(), pedidoEnCurso);
		this.pedidoEnCurso = null;
	}
	
	public void consultarPedido(Integer idPedido) {
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
	
	public Map<Integer, Bebida> getBebidas() {
		return this.bebidas;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos, File archivoBebidas) throws IOException {
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarIngredientes(archivoIngredientes);
		cargarBebidas(archivoBebidas);
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		while (linea != null) {
			String[] info = linea.split(";");
			int precio = Integer.parseInt(info[1]);
			Ingrediente ingrediente = new Ingrediente(info[0], precio);
			this.ingredientes.put(ultimoId(), ingrediente);
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
			this.menuBase.put(ultimoId(), producto);
			linea = br.readLine();
		}
		br.close();
	}
	
	private void cargarBebidas(File archivoMenu) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine();
		while (linea != null) {
			String[] info = linea.split(";");
			int precio = Integer.parseInt(info[1]);
			Bebida bebida = new Bebida(info[0], precio);
			this.bebidas.put(ultimoId(), bebida);
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
			this.combos.put(ultimoId(), combo);
			linea = br.readLine();
		}
		br.close();
	}
	
	private Integer ultimoId() {
		Integer id = this.ultimoIdDisponible;
		this.ultimoIdDisponible += 1;
		return id;
	}
}
