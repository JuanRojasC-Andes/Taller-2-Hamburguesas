package uniandes.dpoo.taller2.procesamiento;

import java.io.File;

public class LoaderData {
	
	public static File cargarArchivo(String path) {
		try {
			return new File(path);	
		} catch(NullPointerException e) {
			System.out.println("Asegurese que el path del archivo es valido");
			throw e;
		}
	}
}
