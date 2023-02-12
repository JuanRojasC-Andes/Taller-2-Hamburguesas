package uniandes.dpoo.taller2.modelo;

public class Ingrediente {
	
	private String nombre;
	private int costoAdicional;
	
	public Ingrediente(String nombre, int costoAdicional) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
				
	}

	public String getNombre() {
		return nombre;
	}


	public int getCostoAdicional() {
		return costoAdicional;
	}
}
