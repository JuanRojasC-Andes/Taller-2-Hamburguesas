package uniandes.dpoo.taller2.procesamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestorDeArchivos {
	
	public static File cargarArchivo(String path) {
		try {
			return new File(path);	
		} catch(NullPointerException e) {
			System.out.println("Asegurese que el path del archivo es valido");
			throw e;
		}
	}
	
	public static BufferedReader cargarArchivoBuffered(String path) throws FileNotFoundException {
		try {
			File archivo = new File(path);
			FileReader lector = new FileReader(archivo);
			BufferedReader buffer = new BufferedReader(lector);
			return buffer;
		} catch(NullPointerException e) {
			System.out.println("Asegurese que el path del archivo es valido");
			throw e;
		}
	}
	
	public static void guardarArchivo(String path, String contenido) throws IOException {
		try {
			File archivo = new File(path);
			FileWriter fw = new FileWriter(archivo);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(contenido);
			writer.close();
		} catch(Exception e) {
			System.out.println("No he podido guardar el archivo " + path);
			throw e;
		}
	}
}
