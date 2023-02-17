package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.taller2.modelo.Combo;
import uniandes.dpoo.taller2.modelo.Ingrediente;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.procesamiento.LoaderData;
import uniandes.dpoo.taller2.procesamiento.Restaurante;

public class ConsolaHamburguesas {
	
	private Restaurante restaurante;

	public static void main(String[] args) throws IOException {
		ConsolaHamburguesas consola = new ConsolaHamburguesas();
		consola.ejecutarAplicacion();
	}
	
	public void cargarDatos() throws IOException {
		File archivoIngredientes = LoaderData.cargarArchivo("./data/ingredientes.txt");
		File archivoMenu = LoaderData.cargarArchivo("./data/menu.txt");
		File archivoCombos = LoaderData.cargarArchivo("./data/combos.txt");
		this.restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	}
	
	public void mostrarOpciones() {
		System.out.println("\nBienvenido a la tienda de Hamburguesas\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Hacer un pedido");
		System.out.println("3. Modificar un pedido");
		System.out.println("4. Cerrar un pedido");
		System.out.println("5. Consultar informacion de un pedido");
		System.out.println("0. Salir de la aplicacion");
	}
	
	public void ejecutarAplicacion() throws IOException
	{
		Restaurante restaurante = new Restaurante();
		this.restaurante = restaurante;
		cargarDatos();

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarOpciones();
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					mostrarMenu();
				else if (opcion_seleccionada == 2)
					recibirPedido();
				else if (opcion_seleccionada == 3)
					recibirPedido();
				else if (opcion_seleccionada == 4)
					cerrarPedido();
				else if (opcion_seleccionada == 5)
					System.out.println("5");
				else if (opcion_seleccionada == 0)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	public void mostrarMenu() {
		String separator = "-".repeat(40);
		Map<Integer, Producto> productos = this.restaurante.getMenuBase();
		System.out.println(String.format("\n%s\n%s\n%s\n", separator, " PRODUCTOS", separator));
		for (Integer key : productos.keySet()) {
			Producto p = productos.get(key);
			System.out.println(String.format("%d. %s: %d", key, p.getNombre(), p.getPrecio()));
		}
		Map<Integer, Combo> combos = this.restaurante.getCombos();
		System.out.println(String.format("\n%s\n%s\n%s\n", separator, " COMBOS", separator));
		for (Integer key : combos.keySet()) {
			Combo c = combos.get(key);
			System.out.println(String.format("%d. %s: %d", key, c.getNombre(), c.getPrecio()));
		}
		Map<Integer, Ingrediente> ingredientes = this.restaurante.getIngredientes();
		System.out.println(String.format("\n%s\n%s\n%s\n", separator, " ADICIONES", separator));
		for (Integer key : ingredientes.keySet()) {
			Ingrediente i = ingredientes.get(key);
			System.out.println(String.format("%d. %s: %d", key, i.getNombre(), i.getCostoAdicional()));
		}
	}
	
	public void recibirPedido() {
		System.out.println("\nPara realizar un pedido ingrese el codigo del item segun su categoria, de lo contario deje en blanco y ENTER para continuar\n");
		System.out.println("Si desea varios items de la misma categoria separe por comas");
		Map<String, ArrayList<Integer>> entradas = ejecutarMenuDePedidos();
		String nombrecliente = input("\nIngrese su nombre");
		String direccionCliente = input("Ingrese su direccion");
		this.restaurante.iniciarPedido(
				nombrecliente, 
				direccionCliente, 
				entradas.get("combos"), 
				entradas.get("productosBase"), 
				entradas.get("adiciones"), 
				entradas.get("ingredientesRemovidos")
				);
	}
	
	public void modificarPedido() {
		System.out.println("\nPara modificar un pedido ingrese el codigo del item segun su categoria, de lo contario deje en blanco y ENTER para continuar\n");
		System.out.println("Si desea varios items de la misma categoria separe por comas");
		Map<String, ArrayList<Integer>> entradas = ejecutarMenuDePedidos();
		this.restaurante.modificarPedido(
				entradas.get("combos"), 
				entradas.get("productosBase"), 
				entradas.get("adiciones"), 
				entradas.get("ingredientesRemovidos")
				);
	}
	
	public void cerrarPedido() throws IOException {
		this.restaurante.cerrarYGuardarPedido();
	}
	
	private Map<String, ArrayList<Integer>> ejecutarMenuDePedidos() {
		Map<String, ArrayList<Integer>> entradas = new HashMap<String, ArrayList<Integer>>();
		entradas.put("combos", validarIds(input("Desea algun combo")));
		entradas.put("productosBase", validarIds(input("Desea algun producto")));
		entradas.put("adiciones", validarIds(input("Desea adicionar un ingrediente")));
		entradas.put("ingredientesRemovidos", validarIds(input("Desea remover un ingrediente")));
		return entradas;		
	}
	
	private ArrayList<Integer> validarIds(String entrada) {
		String[] entradas;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			entradas = entrada.split(",");
		} catch(Exception e) {
			return ids;
		}
		if (entradas.length <= 0) return ids;
		for (int i = 0; i < entradas.length; i++) {
			try {
				String id = entradas[i];
				Integer idInt = Integer.parseInt(id);
				ids.add(idInt);
			} catch(Exception e) {}
		}
		return ids;
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo su eleccion");
			e.printStackTrace();
		}
		return null;
	}

}
