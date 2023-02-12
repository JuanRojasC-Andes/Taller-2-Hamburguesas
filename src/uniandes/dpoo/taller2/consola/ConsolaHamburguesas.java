package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		mostrarOpciones();

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					mostrarMenu();
				else if (opcion_seleccionada == 2)
					System.out.println("2");
				else if (opcion_seleccionada == 3)
					System.out.println("3");
				else if (opcion_seleccionada == 4)
					System.out.println("4");
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
		ArrayList<Producto> productos = this.restaurante.getMenuBase();
		System.out.println(String.format("\n%s\n%s\n%s\n", separator, " PRODUCTOS", separator));
		for (Producto p : productos) {
			System.out.println(String.format("%s: %d", p.getNombre(), p.getPrecio()));
		}
		ArrayList<Combo> combos = this.restaurante.getCombos();
		System.out.println(String.format("\n%s\n%s\n%s\n", separator, " COMBOS", separator));
		for (Combo c : combos) {
			System.out.println(String.format("%s: %d", c.getNombre(), c.getPrecio()));
		}
		ArrayList<Ingrediente> ingredientes = this.restaurante.getIngredientes();
		System.out.println(String.format("\n%s\n%s\n%s\n", separator, " ADICIONES", separator));
		for (Ingrediente i : ingredientes) {
			System.out.println(String.format("%s: %d", i.getNombre(), i.getCostoAdicional()));
		}
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
