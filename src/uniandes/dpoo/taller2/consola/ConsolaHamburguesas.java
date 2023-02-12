package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsolaHamburguesas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Funcionando");
	}
	
	public static void mostrarOpciones() {
		System.out.println("\nBienvenido a la tienda de Hamburguesas\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Hacer un pedido");
		System.out.println("3. Modificar un pedido");
		System.out.println("4. Cerrar un pedido");
		System.out.println("5. Consultar informacion de un pedido");
		System.out.println("0. Salir de la aplicacion");
	}
	
	public void ejecutarOpcion()
	{
		System.out.println("Menu de la Tienda\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					System.out.println("3");
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
	
	public static void mostrarMenu() {
		
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
